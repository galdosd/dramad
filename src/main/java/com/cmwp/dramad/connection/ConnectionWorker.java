package com.cmwp.dramad.connection;

import com.cmwp.dramad.framework.OurActor;

import java.util.*;

import akka.actor.Props;
import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.io.Tcp; 
import akka.io.TcpMessage; 
import akka.io.Inet;

public class ConnectionWorker extends OurActor {

	@Override
	protected boolean dispatchByType( Object msg ) {
		// TODO: replace with Typed Actors or other akka feature if possible
		// if not, move to OurActor and use introspection/annotations
		if( msg instanceof Tcp.Received )				onTcpReceived( (Tcp.Received) msg );
		else return false;
		return true;
	}

	protected void onTcpReceived( Tcp.Received msg ) {
		System.out.println( "We are receiving now");
	}
}
