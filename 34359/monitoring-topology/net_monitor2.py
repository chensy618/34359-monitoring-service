from mininet.topo import Topo
from mininet.net import Mininet
from mininet.node import OVSSwitch, RemoteController
from mininet.link import TCLink
from mininet.log import setLogLevel, info
from mininet.util import dumpNodeConnections
from mininet.cli import CLI

class CustomTopo(Topo):
    def __init__(self, **opts):
        super(CustomTopo, self).__init__(**opts)

        # Add switches
        s1 = self.addSwitch('s1', cls=OVSSwitch, protocols='OpenFlow13')
        s2 = self.addSwitch('s2', cls=OVSSwitch, protocols='OpenFlow13')

        # Add hosts
        h1 = self.addHost('h1', ip='10.0.0.1', mac='00:00:00:00:00:01')
        h2 = self.addHost('h2', ip='10.0.0.2', mac='00:00:00:00:00:02')
        h3 = self.addHost('h3', ip='10.0.0.3', mac='00:00:00:00:00:03')
        h4 = self.addHost('h4', ip='10.0.0.4', mac='00:00:00:00:00:04')

        # Add links with capacity
        self.addLink(h1, s1, port1=0, port2=1, bw=1000, max_queue_size=1000, delay='5ms', loss=0.5)  # 1 Gbps, 5ms delay, 0.5% loss
	self.addLink(h2, s1, port1=0, port2=2, bw=1000, max_queue_size=1000, delay='5ms', loss=0.5)  # 1 Gbps, 5ms delay, 0.5% loss
	self.addLink(s1, s2, port1=3, port2=1, bw=1000, max_queue_size=1000, delay='1ms', loss=0.1)  # 1 Gbps, 1ms delay, 0.1% loss
	self.addLink(h3, s2, port1=0, port2=2, bw=1000, max_queue_size=1000, delay='5ms', loss=0.5)  # 1 Gbps, 5ms delay, 0.5% loss
	self.addLink(h4, s2, port1=0, port2=3, bw=1000, max_queue_size=1000, delay='5ms', loss=0.5)  # 1 Gbps, 5ms delay, 0.5% loss
	
def runCustomTopology():
    # Create the custom topology
    topo = CustomTopo()
    net = Mininet(topo=topo, link=TCLink, controller=None)

    # Add a remote ONOS controller
    controller = RemoteController('c0', ip='127.0.0.1', port=6653)
    net.addController(controller)

    net.start()

    """
    # Generate traffic with specific types
    h1, h4 = net.get('h1', 'h4')
    h2, h3 = net.get('h2', 'h3')

    # HTTP traffic simulation (TCP)
    h1.cmd('iperf -s &')  # Start iperf server on h1
    h4.cmd('iperf -c ' + h1.IP() + ' -t 10 -p 80 &')  # HTTP-like TCP traffic

    # Video streaming simulation (UDP)
    h2.cmd('iperf -s -u &')  # Start iperf UDP server on h2
    h3.cmd('iperf -c ' + h2.IP() + ' -u -b 20M -t 10 &')  # Video-like UDP traffic
    
    # ICMP traffic simulation
    info("Starting ICMP traffic simulation...\n")
    h1.cmd('ping -c 10 ' + h4.IP() + ' &')  # h1 ping h4
    h2.cmd('ping -c 5 ' + h3.IP() + ' &')   # h2 ping h3
    """
    
    # Display network details
    info("\nDumping host connections:\n")
    dumpNodeConnections(net.hosts)
    
    info("\nStarting CLI for interactive exploration...\n")
    CLI(net)

    net.stop()

if __name__ == '__main__':
    setLogLevel('info')
    runCustomTopology()

