package com.cmwp.dramad.dept;

import com.cmwp.dramad.framework.OurActor;
import com.cmwp.dramad.connection.*;

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
	private boolean isListening  = false;

	@Override
	public void preStart() {
		becomeListening(LISTEN_PORT);
	}

	/**
	 * begin the process of opening a socket to listen on the given TCP port
	 * this method is idempotent (if already connected, nothing happens)
	 * 
	 * @param listenPort TCP port to listen on
	 */
	protected void becomeListening( int listenPort) {
		if( isListening ) return;
		final List<Inet.SocketOption> options = new ArrayList<Inet.SocketOption>();
		options.add(TcpSO.reuseAddress(true));
		tcpManager.tell( TcpMessage.bind( getSelf(), new java.net.InetSocketAddress(listenPort), 30, options), getSelf() );
	}

	protected boolean dispatchByType( Object msg ) {
		// TODO: replace with Typed Actors or other akka feature if possible
		// if not, move to OurActor and use introspection/annotations
		if( msg instanceof Tcp.Bound )				onTcpBound( (Tcp.Bound) msg );
		else if( msg instanceof Tcp.Connected )		onTcpConnected( (Tcp.Connected) msg );
		else return false;
		return true;
	}

	protected void onTcpBound( Tcp.Bound msg ) {
		System.out.println( "We are listening now");
		isListening  = true;
	}

	protected void onTcpConnected( Tcp.Connected msg ) {
		ActorRef worker = ourActorOf(ConnectionWorker.class);
		reply( TcpMessage.register( worker ) );
		System.out.println( "We got a connection" );
	}
}
