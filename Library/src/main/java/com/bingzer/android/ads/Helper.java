/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android.ads;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Ricky Tobing
 */
class Helper {

    /**
     * Gets random value from param <code>any</code>
     */
    public static <T> T getRandom(T... any){
        return any[(int) (Math.random() * any.length)];
    }

    /**
     * Returns random from to to
     */
    public static float getRandom(float from, float to){
    	return from + (int) (Math.random() * ((to - from) + 1));
    }
    
    public static int getRandom(int from, int to){
    	return from + (int) (Math.random() * ((to - from) + 1));
    }

    /**
     * CHeck to see if <code>any</code> is either
     * null or empty
     */
    public static boolean isNullOrEmpty(CharSequence any){
        return (any == null || any.length() == 0);
    }

    /**
     * Convert collection <code>any</code> to array
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Class<T> clazz, Collection<T> any){
        // --- java is not really supporting a generic array creation
        // we have to work around it..
        T[] ts = (T[]) Array.newInstance(clazz, any.size());
        
        return any.toArray(ts);
    }

    public static void fastSplit(List<String> list, CharSequence target, String delimeter){
        fastSplit(list, new StringBuilder().append(target), delimeter);
    }

    public static void fastSplit(List<String> list, StringBuilder target, String delimeter){
        int start = 0;
        int end   = target.indexOf(delimeter);
        while(end >= 0){
            list.add(target.substring(start, end));
            start = end + 1;
            end   = target.indexOf(delimeter, start);
        }
    }
    
}
