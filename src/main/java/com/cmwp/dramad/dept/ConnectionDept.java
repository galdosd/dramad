package com.cmwp.dramad.dept;

import com.cmwp.dramad.framework.OurActor;

import java.util.*;

import akka.actor.Props;
import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.io.Tcp; 
import akka.io.TcpSO; 
import akka.io.TcpMessage; 
import akka.io.Inet;

public class ConnectionDept extends OurActor {
	// TODO config/IoC/runtime
	public final int LISTEN_PORT = 6667;
	
	//@Getter
	private boolean listening = false;

	@Override
	public void preStart() {
		becomeListening();
	}

	void becomeListening() {
		if(listening) return;
		// open listening socket on port
		// i hope
		final List<Inet.SocketOption> options = new ArrayList<Inet.SocketOption>();
		options.add(TcpSO.reuseAddress(true));
		tcpManager.tell( TcpMessage.bind( getSelf(), new java.net.InetSocketAddress(LISTEN_PORT), 30, options), getSelf() );
	}

	public void onReceive( Object msg ) {
		if( null != msg ) {
			System.out.println(msg.getClass().getName() + "("+ msg.toString() +") --> " + this.getClass().getName());
		}
		else {
			System.out.println("null message -->" + this.getClass().getName());
		}
		if( msg instanceof Tcp.Bound ) {
			System.out.println( "We are listening now");
			listening = true;
		}
		else if( msg instanceof Tcp.Connected ) {
			System.out.println( "We got a connection" );
		}
		else unhandled(msg);
	}

}
