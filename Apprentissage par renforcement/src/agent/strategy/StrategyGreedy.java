package agent.strategy;

import java.util.List;
import java.util.Random;

import agent.rlagent.RLAgent;
import environnement.Action;
import environnement.Etat;
import environnement.crawler.ActionCrawler;
import environnement.gridworld.ActionGridworld;
import java.util.Arrays;
/**
 * Strategie qui renvoit une action aleatoire avec probabilite epsilon, une action gloutonne (qui suit la politique de l'agent) sinon
 * Cette classe a acces a un RLAgent par l'intermediaire de sa classe mere.
 * @author lmatignon
 *
 */
public class StrategyGreedy extends StrategyExploration
{
    private Random rand = new Random();
    protected Double epsilon;

    public StrategyGreedy(RLAgent agent, double epsilon)
    {
        super(agent);
        
        this.epsilon = epsilon;
    }

    /**
     * @return action selectionnee par la strategie d'exploration
     */
    @Override
    public Action getAction(Etat _e)
    {
        if(rand.nextDouble() > epsilon)
        {
            Action action = getAgent().getPolitique(_e).stream().findFirst().orElse(null);
            if(action == null)
                return getRandom(_e);
            else
                return action;
        }
        else
        {
            return getRandom(_e);
        }
    }
    
    protected Action getRandom(Etat _e)
    {
        List<Action> actions = getAgent().getEnv().getActionsPossibles(_e);
        if(actions.isEmpty())
            return null;
        else
            return actions.get(rand.nextInt(actions.size()));
    }



    public void setEpsilon(double epsilon)
    {
        this.epsilon = epsilon;
    }
}
