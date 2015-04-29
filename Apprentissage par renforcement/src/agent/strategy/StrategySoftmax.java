package agent.strategy;

import agent.rlagent.RLAgent;
import environnement.Action;
import environnement.Etat;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author lmatignon
 *
 */
public class StrategySoftmax extends StrategyExploration
{
    private final Random rand = new Random();
    
    protected Double tau;

    public StrategySoftmax(RLAgent agent, double tau)
    {
        super(agent);
        
        this.tau = tau;
    }
    
    
    /**
     * @return action selectionnee par la strategie d'exploration
     */
    @Override
    public Action getAction(Etat _e)
    {
        double index = rand.nextDouble();
        
        double sum = getActions(_e)
                .stream()
                .mapToDouble(b -> Math.exp(getAgent().getQValeur(_e, b)/tau))
                .sum();
        
        for(Action a : getActions(_e))
        {
            index -= Math.exp(getAgent().getQValeur(_e, a)/tau) / sum;
            if(index <= 0)
                return a;
        }
        
        return null;
    }
    
    protected List<Action> getActions(Etat _e)
    {
        return getAgent().getEnv().getActionsPossibles(_e);
    }

    public void setTau(double tau)
    {
        this.tau = tau;
    }
}
