package se.bikku.sitevisionbooli;

import org.apache.velocity.context.Context;
import se.bikku.javabooli.Booli;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

public class Portlet extends GenericSiteVisionPortlet
{
   @Override
   public void doView(RenderRequest aRenderRequest, RenderResponse aRenderResponse)
           throws PortletException, IOException
   {
      aRenderResponse.setContentType("text/html");

      PortletPreferences prefs = aRenderRequest.getPreferences();
      String location = prefs.getValue("location", "Stockholm");

      Booli booli = new Booli("javabooli", "6b0fjIG8JFLgtzywGwOrUu1YppsbZkiY5WdyzOFP");

      Context context = getContext(aRenderRequest);
      context.put("listings", booli.search(location));

      super.doView(aRenderRequest, aRenderResponse);
   }
}