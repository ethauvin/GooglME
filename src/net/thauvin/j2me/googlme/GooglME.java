/*
 * @(#)GooglME.java
 *
 * Copyright (c) 2004, Erik C. Thauvin (http://www.thauvin.net/erik/)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the authors nor the names of its contributors may be
 * used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $Id$
 *
 */
package net.thauvin.j2me.googlme;

import javax.microedition.io.*;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import javax.wireless.messaging.*;


/**
 * The <code>GooglME</code> class implements a simple front-end for Google SMS.
 *
 * @author <a href="http://www.thauvin.net/erik/">Erik C. Thauvin</a>
 * @version $Revision$, $Date$
 *
 * @created October 8, 2004
 * @since 1.0
 */
public class GooglME extends MIDlet implements CommandListener, Runnable
{
	/**
	 * The <code>About</code> command.
	 */
	protected /* final */ Command aboutCommand = new Command("About", Command.SCREEN, 5);

	/**
	 * The <code>Back</code> command.
	 */
	protected /* final */ Command backCommand = new Command("Back", Command.BACK, 2);

	/**
	 * The <code>Clear</code> command.
	 */
	protected /* final */ Command clearCommand = new Command("Clear", Command.SCREEN, 3);

	/**
	 * The <code>Exit</code> command.
	 */
	protected /* final */ Command exitCommand = new Command("Exit", Command.EXIT, 2);

	/**
	 * The <code>Help</code> command.
	 */
	protected /* final */ Command helpCommand = new Command("Help", Command.HELP, 4);

	/**
	 * The <code>Send</code> command.
	 */
	protected /* final */ Command sendCommand = new Command("Send", Command.SCREEN, 2);

	/**
	 * The application name.
	 */
	protected /* final */ String appName = "GooglME";

	/**
	 * The application version.
	 */
	protected /* final */ String appVersion = "0.1";
	private Display display;

	/**
	 * The help screen.
	 */
	private /* final */ HelpScreen helpScreen;

	/**
	 * The main screen.
	 */
	private /* final */ MainScreen mainScreen;
	private String query;
	private int action;

	/**
	 * Creates a new GooglME instance.
	 */
	public GooglME()
	{
		super();

		mainScreen = new MainScreen(this);
	}

	/**
	 * Performs a command.
	 *
	 * @param c The command action.
	 * @param d The diplayable screen.
	 */
	public void commandAction(Command c, Displayable d)
	{
		if (c == exitCommand)
		{
			exit();
		}
		else if (c == aboutCommand)
		{
			msg("About " + appName, appName + ' ' + appVersion + "\nCopyright 2004\nErik C. Thauvin\nerik@thauvin.net",
				d, false);
		}
		else if (c == clearCommand)
		{
			mainScreen.queryFld.setString("");
			Util.setCurrentItem(display, (Item) mainScreen.queryFld);
		}
		else if (c == sendCommand)
		{
			query = mainScreen.queryFld.getString();
			action = mainScreen.actionPopup.getSelectedIndex();

			if ((query != null) && (query.length() > 0))
			{
				new Thread(this).start();
			}
			else
			{
				msg("Invalid Input", "Please specify a query.", d, true);
				Util.setCurrentItem(display, (Item) mainScreen.queryFld);
			}
		}
		else if (c == helpCommand)
		{
			if (helpScreen == null)
			{
				helpScreen = new HelpScreen(this);
			}

			display.setCurrent(helpScreen);
		}
		else if (c == backCommand)
		{
			display.setCurrent(mainScreen);
		}
	}

	/**
	 * Executes the thread.
	 */
	public void run()
	{
		sendSMS();
	}

	/**
	 * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	 */
	protected void destroyApp(boolean b)
					   throws MIDletStateChangeException
	{
		notifyDestroyed();
	}

	/**
	 * @see javax.microedition.midlet.MIDlet#pauseApp()
	 */
	protected void pauseApp()
	{
		;
	}

	/**
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 */
	protected void startApp()
					 throws MIDletStateChangeException
	{
		display = Display.getDisplay(this);
		display.setCurrent(mainScreen);
	}

	// Exits the application.
	private void exit()
	{
		try
		{
			destroyApp(false);
		}
		catch (MIDletStateChangeException e)
		{
			; // Do nothing 
		}
	}

	// Displays a message/error dialog.
	private void msg(String title, String msg, Displayable d, boolean isError)
	{
		/* final */ Alert alert = new Alert(title, msg, null, AlertType.INFO);

		if (isError)
		{
			alert.setType(AlertType.ERROR);
		}

		alert.setTimeout(Alert.FOREVER);
		display.setCurrent(alert, d);
	}

	// Sends the SMS.
	private void sendSMS()
	{
		/* final */ String address = "sms://46645";
		MessageConnection conn = null;

		try
		{
			conn = (MessageConnection) Connector.open(address);

			/* final */ TextMessage msg = (TextMessage) conn.newMessage(MessageConnection.TEXT_MESSAGE);
			msg.setAddress(address);

			// Froogle
			if (action == 1)
			{
				msg.setPayloadText("f " + query);
			}

			// Google
			else if (action == 2)
			{
				msg.setPayloadText("g " + query);
			}

			// Definition
			else if (action == 3)
			{
				msg.setPayloadText("d " + query);
			}
			else
			{
				msg.setPayloadText(query);
			}

			conn.send(msg);

			msg("SMS Sent", "The text message was sent.", mainScreen, false);
		}
		catch (Exception e)
		{
			msg("SMS Error", "The text message could not be sent: " + e.getMessage(), mainScreen, true);
		}
		finally
		{
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (Exception ignore)
			{
				; // Do nothing;
			}
		}
	}
}
