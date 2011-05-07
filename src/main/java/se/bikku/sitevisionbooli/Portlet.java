package se.bikku.sitevisionbooli;

import org.apache.velocity.context.Context;
import se.bikku.javabooli.Booli;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * SiteVision portlet to show real estate listings from Booli.se.
 */
public class Portlet extends GenericSiteVisionPortlet {
  @Override
  public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
    response.setContentType("text/html");

    PortletPreferences prefs = request.getPreferences();
    String location = prefs.getValue("location", "Stockholm");

    Booli booli = new Booli(prefs.getValue("callerId", ""), prefs.getValue("key", ""));

    Context context = getContext(request);

    try {
      context.put("listings", booli.search(location));
    } catch(Exception e) {
      ResourceBundle translations = ResourceBundle.getBundle("translations", request.getLocale());
      fail(translations.getString("portletIsNotConfigured"), response);
    }

    super.doView(request, response);
  }
}