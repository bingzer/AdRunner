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

import java.util.LinkedList;
import java.util.List;

/**
 * Keyword Utils
 */
@SuppressWarnings("UnusedDeclaration")
public class KeywordUtils {

    static final int MINIMUM_LENGTH = 3;

    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * Produces any keywords from any string
     */
    public static String[] getKeywords(String any, String delimiter, String... defaultKeywords){
        try{
            if(!Helper.isNullOrEmpty(any)){
                List<String> keywordList = new LinkedList<String>();
                Helper.fastSplit(keywordList, any, delimiter);

                // make sure we accept each keyword
                filter(keywordList);

                // if empty use the defaults
                if(keywordList.isEmpty()) throw new RuntimeException();

                // convert to string array
                return Helper.toArray(String.class, keywordList);
            }
            else throw new RuntimeException();
        }
        catch (Throwable e){
            if(defaultKeywords != null) return defaultKeywords;
            return new String[0];
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////

    static void filter(List<String> keywordList){
        for(int i = 0; i < keywordList.size(); i++){
            if(!accept(keywordList.get(i))){
                keywordList.remove(i);
            }
        }
    }

    static boolean accept(String any){
        return !Helper.isNullOrEmpty(any) && any.trim().length() > MINIMUM_LENGTH;
    }

}
