using System;
using System.Collections.Generic;

namespace birds.strategyPattern
{
	
	class MainClass
	{
		public static void Main(string[] args)
		{
			var flighted = new List<Flighted>{new Pigeon(), new Hawk(), new FrigateBird()};
			
			flighted.ForEach(x => x.Fly());
		
			var swimmers = new List<Swimmer>{new Pigeon(), new Hawk(), new Penguin()};

			swimmers.ForEach(x => x.Swim());
		}
	}
	
	public class Pigeon : Bird<Flighted, Swimmer>
	{
		public Pigeon() : base(Fly.Message("I'm a good flier")) {}
	}

	public class Hawk : Bird<Flighted, Swimmer>
	{
		public Hawk() : base(Fly.Message("I'm an excellent flier")) {}
	}

	public class FrigateBird : Bird<Flighted, UnableToSwim>
	{
		public FrigateBird() : base(Fly.Message("I'm an excellent flier")) {}
	}

	public class Penguin : Bird<UnableToFly, Swimmer>
	{
		public Penguin() : base(Unable.ToFly){}
	}
	
	public abstract class Bird<F,S> where F : FlyingBehaviour where S : SwimmingBehaviour, new()
	{
		readonly F flyingBehaviour;
		readonly S swimmingBehaviour;

		public Bird(Func<F> f)
		{
			this.flyingBehaviour = f();
			this.swimmingBehaviour = new S();
		}
		
		
		public static implicit operator F(Bird<F, S> bird) 
		{
			return bird.flyingBehaviour;
		}
		
		public static implicit operator S(Bird<F, S> bird) 
		{
			return bird.swimmingBehaviour;
		}

	}

	public interface FlyingBehaviour
	{
		
	}

	public class Flighted : FlyingBehaviour
	{
		string flyMessage;
		public Flighted(string flyMessage) 
		{
			this.flyMessage = flyMessage;
		}

		public void Fly()
		{
			Console.WriteLine(flyMessage);
		}
		
	}

	public class UnableToFly : FlyingBehaviour
	{
	}

	public interface SwimmingBehaviour 
	{
	
	}

	public class Swimmer : SwimmingBehaviour
	{
		public void Swim()
		{
			Console.WriteLine("I'm swimming");
		}
	}

	public class UnableToSwim : SwimmingBehaviour
	{
		
	}
	
	public class Unable 
	{
		public static Func<UnableToFly> ToFly = () => new UnableToFly();
	}
	
	public class Fly
	{
		public static Func<Flighted> Message(string message) 
		{
			return () => new Flighted(message);
		}
	}

	
}