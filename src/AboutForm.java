/*
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License version 2 as published by
 *  the Free Software Foundation
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 * Kirodict 1.0 <info@mypapit.net>
 * Copyright 2008 Mohammad Hafiz bin Ismail. All rights reserved.
 *
 * AboutForm.java
 * Generic, reusable AboutForm
 */



import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.IOException;
import javax.microedition.io.ConnectionNotFoundException;

public class AboutForm extends Form implements ItemCommandListener
{
	private ImageItem logoItem;
	private Command cmdBrowse;
	private StringItem hyperlink;
	private MIDlet midlet;
	public static Command DISMISS_COMMAND;


/**
 * Class Constructor
 *
 * @param  title about form title
 * @param  label logo label, usually application or company's name.
 */
	public AboutForm (String title,String label)
	{
		super(title);
		logoItem = new ImageItem(label,null, Item.LAYOUT_CENTER | Item.LAYOUT_NEWLINE_AFTER , "logo");
		this.append(logoItem);
		this.initBackCommand();
	}

/**
 * Class Constructor
 *
 * @param  title about form title.
 * @param  label logo label, usually the application or company's name.
 * @param  logo about form logo, usually application icon or company's logo.
 */

	public AboutForm (String title, String label, Image logo )
	{
		super(title);
		logoItem = new ImageItem(label,logo,Item.LAYOUT_VCENTER | Item.LAYOUT_NEWLINE_AFTER, "logo");

		this.append(logoItem);
		this.initBackCommand();

	}

	/**
	 * Class Constructor
	 *
	 * @param  title about form title.
	 * @param  label logo label, usually the application or company's name.
	 * @param  resource location of  about form logo, usually application icon or company's logo.
	 */

	public AboutForm (String title, String label, String resource )
	{
		super(title);
		Image img;

		try {
			img = Image.createImage(resource);
		} catch (IOException ioex) {
			img = null;
		}

		logoItem = new ImageItem(label,img,Item.LAYOUT_CENTER | Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_EXPAND, "logo");

		this.append(logoItem);
		this.initBackCommand();
	}

/**
* Set the logo and label layout
* @param layout combination of layout directives
* @see Item
*/
	public void setLayout(int layout){
		logoItem.setLayout(layout);
	}
/**
* Set Copyright notice
* @param name of the copyright holder
* @param year the year it was copyrighted
*/

	public void setCopyright(String owner, String year)
	{
		StringItem notice = new StringItem(null,"Copr. " + year +" " +  owner + ".", StringItem.PLAIN);
		notice.setLayout(Item.LAYOUT_CENTER | Item.LAYOUT_NEWLINE_AFTER);
		this.append( notice);

	}
/**
* Hyperlink to website, usually application website or corporate website.
* @param link url of the website
* @param midlet the current midlet object
*/

	public void setHyperlink(String link, MIDlet midlet)
	{
	  hyperlink = new StringItem(null, link, Item.HYPERLINK);
	  this.append (hyperlink);
	 hyperlink.setLayout(Item.LAYOUT_CENTER | Item.LAYOUT_NEWLINE_AFTER);
	 cmdBrowse = new Command("Browse",Command.SCREEN,10);
	 hyperlink.setDefaultCommand(cmdBrowse);
	 hyperlink.setItemCommandListener(this);
	 this.midlet = midlet;
	}


	public void commandAction(Command c, Item i)
	{
		if (c == cmdBrowse) {
			try {
			 	midlet.platformRequest(hyperlink.getText());
			} catch (ConnectionNotFoundException cnf){

			} catch (Exception ex) {

			}



		}

	}

private void initBackCommand()
	{
		DISMISS_COMMAND = new Command("Back",Command.BACK,0);
		this.addCommand(DISMISS_COMMAND);
	}

public void setBackCommand(String title)
{
	this.removeCommand(DISMISS_COMMAND);
	DISMISS_COMMAND = new Command(title,Command.BACK,0);
	this.addCommand(DISMISS_COMMAND);
}

/**
* Removes command from about form interface
* @param cmd Command to be removed
*/
public void removeCommand(Command cmd)
{
	if (cmd == DISMISS_COMMAND) {
		return;
	}

}




}
