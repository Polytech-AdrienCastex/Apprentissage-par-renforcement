package simu;

import javax.swing.SwingUtilities;

import vueGridworld.VueGridworldRL;
import agent.rlagent.QLearningAgent;
import agent.rlagent.RLAgent;
import environnement.gridworld.GridworldEnvironnement;
import environnement.gridworld.GridworldMDP;

public class testQLAgentGridworld {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		  SwingUtilities.invokeLater(new Runnable(){
				public void run(){

					GridworldMDP gmdp = GridworldMDP.getBookGrid();
					
					GridworldEnvironnement g = new GridworldEnvironnement(gmdp);
					gmdp.setProba(0.1);
					double gamma=0.9;
					double alpha=0.1;
					int nbA=5;
					int nbS = gmdp.getNbEtats();
					
					
					//RLAgent a = new QLearningAgentb(alpha,gamma,g,nbS,nbA);
					RLAgent a = new QLearningAgent(alpha,gamma,g);
					a.DISPRL = true;
					VueGridworldRL vue = new VueGridworldRL(g,a);			
									
					vue.setVisible(true);
				
				}
			});

	}
}
