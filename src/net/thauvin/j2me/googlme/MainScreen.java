/*
 * @(#)MainScreen.java
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

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;


/**
 * The <code>MainScreen</code> class implements a form used to gather the data needed to perform a Google SMS query.
 *
 * @author <a href="http://www.thauvin.net/erik/">Erik C. Thauvin</a>
 * @version $Revision$, $Date$
 *
 * @created October 8, 2004
 * @since 1.0
 */
public class MainScreen extends Form
{
	/**
	 * The query action popup.
	 */
	protected /* final */ ChoiceGroup actionPopup;

	/**
	 * The query field.
	 */
	protected /* final */ TextField queryFld;

	/**
	 * Creates a new MainScreen object.
	 *
	 * @param midlet The MIDlet instance.
	 */
	public MainScreen(GooglME midlet)
	{
		super(midlet.appName);

		queryFld = new TextField("Query: ", "", 25, TextField.ANY);
		Util.setInitialInputMode(queryFld, "MIDP_LOWERCASE_LATIN");
		actionPopup =
			Util.getChoiceGroup("Type: ", new String[] { "Google SMS", "Froogle Prices", "Google Search", "Definition" });
		actionPopup.setSelectedIndex(0, true);
		append(queryFld);
		append(actionPopup);

		addCommand(midlet.helpCommand);
		addCommand(midlet.aboutCommand);
		addCommand(midlet.clearCommand);
		addCommand(midlet.exitCommand);
		addCommand(midlet.sendCommand);

		setCommandListener(midlet);
	}
}
