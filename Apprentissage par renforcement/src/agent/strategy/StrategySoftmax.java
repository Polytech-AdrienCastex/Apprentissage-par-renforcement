package agent.strategy;

import agent.rlagent.RLAgent;
import environnement.Action;
import environnement.Etat;
import environnement.gridworld.ActionGridworld;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author lmatignon
 *
 */
public class StrategySoftmax extends StrategyExploration
{
    private Random rand = new Random();
    
    protected Double tau;

    public StrategySoftmax(RLAgent agent, double tau)
    {
        super(agent);
        
        this.tau = tau;
    }
    
    protected List<Action> getActions(Etat e)
    {
        return getAgent().getEnv().getActionsPossibles(e);
    }

    @Override
    public Action getAction(Etat _e)
    {
        Map<Action, Double> valeurs = new HashMap<>();
        /*
        getActions().stream()
                .forEach(a ->
                {
                    Double value = (Math.exp(getAgent().getQValeur(_e, a)/tau)) / ();
                    valeurs.put(a, value);
                });
        
        getAgent().getQValeur(_e, null)*/
        
        return null;
    }

    public void setTau(double tau)
    {
        this.tau = tau;
    }
}
