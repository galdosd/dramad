package com.cmwp.dramad;
import com.cmwp.dramad.framework.OurActor;
import akka.actor.Props;
import akka.actor.ActorSystem;
import akka.actor.ActorRef;



public class DaemonActor extends OurActor {
	public static ActorRef actorFrom( ActorSystem system ) {
		return
			(null!=system) 
			? system.actorOf(Props.create(DaemonActor.class))
			: null
		;
	}

	/*
	 * set up client listener socket(s)
	 */
	@Override
	public void preStart() {

		System.out.println( " Startin listener sockkits !" );
	}

	public void onReceive( Object msg ) {
		System.out.println( " We got signal ! " );
	}

}

