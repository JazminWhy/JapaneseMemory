package com.example.jasmin.japanesememory;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Jasmin on 19/01/2018.
 */

public class ButtonMap extends HashMap<Button, Character> {

    public Set<Button> getKeyByValue(Character value) {
        Set<Button> keys = new HashSet<Button>();
        for (Entry<Button, Character> entry : this.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }


 public ButtonMap randomize(){
       ButtonMap bm = this;
     ArrayList<Character> al = new ArrayList<Character>();

       Iterator<Entry<Button,Character>> itB = bm.entrySet().iterator();
    while(itB.hasNext()){
                Entry<Button,Character> b = itB.next();
            al.add(b.getValue());
    }
     Collections.shuffle(al);

    itB =  bm.entrySet().iterator();
    int counter = 0;
     while(itB.hasNext()){
         Entry<Button,Character> b = itB.next();
         bm.put(b.getKey(),al.get(counter));
         counter++;
     }
     return bm;
    }
}



