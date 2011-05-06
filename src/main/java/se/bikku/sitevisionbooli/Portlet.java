package se.bikku.sitevisionbooli;

import se.bikku.javabooli.Booli;
import se.bikku.javabooli.Listing;

import javax.portlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class Portlet extends SiteVisionDispatcher
{
   protected void doConfig(RenderRequest aRenderRequest, RenderResponse aRenderResponse)
           throws PortletException, IOException
   {
      PrintWriter writer = aRenderResponse.getWriter();

      if (hasWritePermission(aRenderRequest))
      {
         PortletPreferences prefs = aRenderRequest.getPreferences();
         PortletURL actionURL = aRenderResponse.createActionURL();

         writer.println("<strong>Update of the portlet configuration shared among all users</strong><br />");
         writer.println("<form action=\"" + actionURL.toString() + "\" method=\"post\">");

         for (Enumeration enm = prefs.getNames(); enm.hasMoreElements();)
         {
            String name = (String) enm.nextElement();
            String value = prefs.getValue(name, "");
            writer.println("<label for=\"" + name + "\">" + name + ": </label>");
            writer.println("<input id=\"" + name + "\" name=\"" + name + "\" type=\"text\" value=\"" + value + "\" /><br />");
         }
         writer.println("<input type=\"submit\" value=\"Save\" />");
         writer.println("</form>");
      } else
      {
         writer.println("<strong>You are not allowed to update the portlet configuration</strong>");
      }
   }

   protected void doView(RenderRequest aRenderRequest, RenderResponse aRenderResponse)
           throws PortletException, IOException
   {
      PortletPreferences prefs = aRenderRequest.getPreferences();
      PrintWriter writer = aRenderResponse.getWriter();

      for (Enumeration enm = prefs.getNames(); enm.hasMoreElements();)
      {
         String name = (String) enm.nextElement();
         String value = prefs.getValue(name, "");
         writer.println(name + ": " + value + "<br />");
      }

    Booli booli = new Booli("javabooli", "6b0fjIG8JFLgtzywGwOrUu1YppsbZkiY5WdyzOFP");

    for (Listing listing : booli.search(prefs.getValue("location", "Stockholm")))
     {
       String imageUrl = listing.getImageUrl(Integer.parseInt(prefs.getValue("longestSide", "0"), 10));
       if (imageUrl != null)
       {
        writer.println("<li><a href=\"" + listing.getListingUrl() + "\"><img src=\"" + imageUrl + "\" alt=\"\"/></a><br />" + listing.getPriceForSale() + "</li>"); 
       }
     }

   }

   public void processAction(ActionRequest anActionRequest, ActionResponse anActionResponse)
           throws PortletException, IOException
   {
      if (hasWritePermission(anActionRequest))
      {
         PortletPreferences prefs = anActionRequest.getPreferences();
         for (Enumeration enm = prefs.getNames(); enm.hasMoreElements();)
         {
            String name = (String) enm.nextElement();
            String newValue = anActionRequest.getParameter(name);
            if (newValue != null)
            {
               prefs.setValue(name, newValue);
            }
         }
         prefs.store();
      }
      anActionResponse.setPortletMode(PortletMode.VIEW);
   }
}