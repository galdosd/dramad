package com.cmwp.dramad.dept;

import com.cmwp.dramad.framework.OurActor;

import akka.actor.Props;
import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.io.Tcp; 


public class ConnectionDept extends OurActor {
	// TODO config/IoC/runtime
	public final int LISTEN_PORT = 6667;
	
	//@Getter
	private boolean connected = false;

	public static ActorRef actorFrom( ActorRefFactory system ) {
		return
			(null!=system) 
			? system.actorOf(Props.create(ConnectionDept.class))
			: null
		;
	}

	@Override
	public void preStart() {
		becomeConnected();
	}

	void becomeConnected() {
		if(connected) return;
		// open listening socket on port
		// i hope
		tcpManager.tell( new Tcp.Bind( getSelf(), new java.net.InetSocketAddress(LISTEN_PORT), 30, null /*XXX is null ok here? */), getSelf() ); 
	}

	public void onReceive( Object msg ) {
		if( null != msg ) {
			System.out.println(msg.getClass().getName() + " --> " + this.getClass().getName());
		}
		if( msg instanceof Tcp.Bound ) {
			System.out.println( "We are connected now");
			connected = true;
		}
		System.out.println( "ConnectionDept: We got signal too ! " );
	}

}
