import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


@WebServlet("/monitoring")
public class MonitoringServlet extends HttpServlet {
    private RestAPI restAPI;
    private Map<String, Port> oldPortStates = new HashMap<>();
    private final long linkCapacityBps = 1_000_000_000L; // 链路容量（1 Gbps）
    private BandwidthUtilizationCalculator calculator;

    @Override
    public void init() throws ServletException {
        super.init();
        restAPI = new RestAPI();
        calculator = new BandwidthUtilizationCalculator(linkCapacityBps); // 初始化计算器
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch and parse link data from ONOS API
            String linksJson = restAPI.fetchNetworkData("links");
            String hostsJson = restAPI.fetchNetworkData("hosts");
            String statisticsJson = restAPI.fetchNetworkData("statistics/ports");
            String flowsJson = restAPI.fetchNetworkData("flows");

            List<Link> linksData = restAPI.parseLinksResponse(linksJson);
            List<Host> hostsData = restAPI.parseHostsResponse(hostsJson);
            Map<String, Port> newPortStates = restAPI.parseStatisticsResponse(statisticsJson);
            List<Flow> flowsData = restAPI.parseFlowsResponse(flowsJson);

            // 计算链路带宽利用率
            Map<String, LinkBandwidth> linkBandwidthMap = calculator.calculateLinkBandwidthUtilization(linksData, oldPortStates, newPortStates);

            // 更新旧端口状态
            oldPortStates.clear();
            oldPortStates.putAll(newPortStates);

            // Set link data as request attribute
            request.setAttribute("onosLinksData", linksData);
            request.setAttribute("onosHostsData", hostsData);
            request.setAttribute("linkBandwidthData", linkBandwidthMap);
            request.setAttribute("onosFlowsData", flowsData);

            // Forward to index.jsp for rendering
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Error fetching data from ONOS API: " + e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
