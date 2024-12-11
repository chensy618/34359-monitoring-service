import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/monitoring")
public class MonitoringServlet extends HttpServlet {

    private RestAPI restAPI;

    @Override
    public void init() throws ServletException {
        super.init();
        restAPI = new RestAPI();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch and parse link data from ONOS API
            String linksJson = restAPI.fetchNetworkData("links");
            String[][] linksData = restAPI.parseLinksResponse(linksJson);

            // Set link data as request attribute
            request.setAttribute("onosLinksData", linksData);

            // Forward to index.jsp for rendering
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error fetching data from ONOS API: " + e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
