/*
 * @(#)HelpScreen.java
 *
 * Copyright (c) 2004-2005, Erik C. Thauvin (http://www.thauvin.net/erik/)
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

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;


/**
 * The <code>HelpScreen</code> class implements a form used to display some help.
 *
 * @author  <a href="http://www.thauvin.net/erik/">Erik C. Thauvin</a>
 * @version $Revision$, $Date$
 * @created October 8, 2004
 * @since   1.0
 */
public class HelpScreen extends Form
{
	/**
	 * Creates a new HelpScreen instance.
	 *
	 * @param midlet The MIDlet instance.
	 */
	public HelpScreen(GooglME midlet)
	{
		super(GooglME.APP_NAME + " Help");

		append(new StringItem(GooglME.CHOICE_GOOGLE_SMS + ": ",
							  "To get business listings, enter business name and city, state or zip. To get phone numbers, enter name of person and city, state or zip.\n\n"));
		append(new StringItem(GooglME.CHOICE_GOOGLE_LOCAL + ": ",
							  "To get local business listings, enter business name. To get local phone numbers, enter name of person. The permanent location setting is automatically added to your query.\n\n"));
		append(new StringItem(GooglME.CHOICE_FROOGLE_PRICES + ": ", "For product prices, enter the product name.\n\n"));
		append(new StringItem(GooglME.CHOICE_GOOGLE_SEARCH + ": ",
							  "For Google search snippets, enter the search terms.\n\n"));
		append(new StringItem(GooglME.CHOICE_MOVIE_SHOWTIMES + ": ",
							  "To get movie showtimes, enter the movie's title followed by your location (a zip code or city and state). For a listing of theaters, enter 'theaters' followed by your location. For a listing of movies playing, enter 'movies' followed by your location.\n\n"));
		append(new StringItem(GooglME.CHOICE_LOCAL_SHOWTIMES + ": ",
							  "To get local movie showtimes, enter the movie's title. For a listing of theaters near you, enter 'theaters'. For a listing of movies playing near you, enter 'movies'. The permanent location setting is automatically added to your query.\n\n"));
		append(new StringItem(GooglME.CHOICE_DEFINITION + ": ", "For definitions, enter the word or term.\n\n"));
		append(new StringItem(GooglME.CHOICE_WEATHER + ": ",
							  "To get the latest weather conditions and four-day forecast for a particular U.S. location, just enter your location, zip or city and state.\n\n"));

		addCommand(GooglME.COMMAND_BACK);

		setCommandListener(midlet);
	}
}
