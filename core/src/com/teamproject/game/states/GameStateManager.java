package com.teamproject.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * This class manages state objects through stack class.
 */
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() { states = new Stack<State>(); }

    public void push(State state) { states.push(state); }

    public void pop() { states.pop().dispose(); }

    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt) { states.peek().update(dt); }

    public void render(SpriteBatch sb) { states.peek().render(sb); }
}