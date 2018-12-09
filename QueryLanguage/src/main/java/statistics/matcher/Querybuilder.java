/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author Niko
 */
public class Querybuilder {

    private Matcher m;

    public Querybuilder() {
        this.m = new All();
    }

    public Matcher build() {
        Matcher haku = m;
        m = new All();
        return haku;
    }

    public Querybuilder playsIn(String team) {
        m = new And(m,
                new PlaysIn(team)
        );
        return this;
    }

    public Querybuilder hasAtLeast(int value, String category) {
        m = new And(m,
                new HasAtLeast(value, category)
        );
        return this;
    }

    public Querybuilder hasFewerThan(int value, String fieldName) {
        m = new And(m,
                new HasFewerThan(value, fieldName)
        );
        return this;
    }

    public Querybuilder oneOf(Matcher m1, Matcher m2) {
        m = new And(m,
                new Or(m1, m2)
        );
        return this;
    }

    public Querybuilder not(Matcher matcher) {
        m = new And(m,
                new Not(matcher)
        );
        return this;
    }
}
