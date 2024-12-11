from mininet.net import Mininet
from mininet.node import RemoteController,OVSKernelSwitch
from mininet.link import TCLink
from mininet.cli import CLI

def simpleTopology():
    net = Mininet(controller=RemoteController,switch=OVSKernelSwitch, link=TCLink)

    # Add a controller
    c0 = net.addController('c0', controller=RemoteController, ip='127.0.0.1', port=6653)

    # Add switches
    s1 = net.addSwitch('s1', protocols='OpenFlow13')
    s2 = net.addSwitch('s2', protocols='OpenFlow13')

    # Add hosts
    h1 = net.addHost('h1', ip='10.0.0.1', mac='00:00:00:00:00:01')
    h2 = net.addHost('h2', ip='10.0.0.2', mac='00:00:00:00:00:02')

    # Add links with bandwidth and delay
    net.addLink(h1, s1, bw=50, delay='5ms')
    net.addLink(s1, s2, bw=100, delay='10ms')
    net.addLink(s2, h2, bw=50, delay='5ms')

    # Start the network
    net.build()
    c0.start()
    s1.start([c0])
    s2.start([c0])

    # Test the network
    CLI(net)

    # Stop the network
    net.stop()

if __name__ == '__main__':
    simpleTopology()

