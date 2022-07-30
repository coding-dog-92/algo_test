package com.algo.company.booking;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Solution {
    /*
     * This class defines the basic B.com pair for keyed stream
     */
    private static class BKeyedEntry<K,V> {
        private K key;
        private V value;
        BKeyedEntry(K key, V value){
            this.key = key;
            this.value = value;
        }
        K getKey() { return key; }
        V getValue() { return value; }
    }

    /*
     * This class defines the basic B.com stream with filter and map methods
     * Use keyBy to generate a stream with Key, Value structure
     */
    static class BStream<T> {
        private List<T> data;
        BStream(List<T> data) {this.data = data;}

        BStream<T> filter(Predicate<? super T> predicate) {
            return new BStream<>(data.stream().filter(predicate).collect(Collectors.toList()));
        }

        <R> BStream<R> map(Function<? super T,? extends R> mapper) {
            return new BStream<>(data.stream().map(mapper).collect(Collectors.toList()));
        }

        <K> BKeyedStream<K,T> keyBy(Function<? super T,? extends K> keySelector) {
            return new BKeyedStream<>(
                    data.stream()
                            .map( x -> new BKeyedEntry<K,T>(keySelector.apply(x), x))
                            .collect(Collectors.toList())
            );
        }

        List<T> collectAsList() { return new ArrayList<>(data); }
    }

    /*
     * This class defines the basic B.com stream that operates on streams with keys and values
     */
    static class BKeyedStream<K,V> {
        private List<BKeyedEntry<K,V>> data;
        BKeyedStream(List<BKeyedEntry<K,V>> data) {this.data = data;}

        BStream<V> reduceByKey(BinaryOperator<V> reducer) {
            Map<K, V> result = new HashMap<>();
            for (BKeyedEntry<K, V> keyedEntry : data) {
                if (result.containsKey(keyedEntry.getKey())) {
                    V value = result.get(keyedEntry.getKey());
                    result.put(keyedEntry.getKey(), reducer.apply(value, keyedEntry.getValue()));
                } else {
                    result.put(keyedEntry.getKey(), keyedEntry.getValue());
                }
            }

            return new BStream<>(new ArrayList<>(result.values()));
        }

        List<BKeyedEntry<K,V>> collectAsList() {return new ArrayList<>(data);}
    }
    // don't change this class, it defines the input message in the stream
    static class InputMessage {
        String hotel_area_code;
        int hotel_id;
        boolean is_user_logged_in;
        String unique_user_id;
    }

    // don't change this class, it defines the output message in the stream
    static class OutputMessage {
        String hotel_area_code;
        int hotel_id;
        int views;
        OutputMessage(String hotel_area_code, int hotel_id, int views){
            this.hotel_area_code = hotel_area_code;
            this.hotel_id = hotel_id;
            this.views = views;
        }
    }

    // this is a helper class, change it as you wish, or build new ones as needed
    static class PageviewCounter {
        int hotel_id;
        String hotel_area_code;
        int count;
        PageviewCounter(int hotel_id, String hotel_area_code, int count) {
            this.hotel_id = hotel_id;
            this.hotel_area_code = hotel_area_code;
            this.count = count;
        }
    }

    // Complete the stream function below.
// Currently it returns a stream with pageviews counts for all the hotels in France (FR).
// Modify the function so it returns for each area code the hotel with the highest number of views by logged-in users.
    public static BStream<OutputMessage> process(BStream<InputMessage> input) {
//        return input.filter( x -> x.hotel_area_code.equals("FR")) // filter for hotels in FR
        return input.filter( x -> x.is_user_logged_in) // filter for hotels in FR
                .map(
// Map to PageviewCounter helper class with counter set to 1
                        x -> new PageviewCounter(x.hotel_id, x.hotel_area_code,1)
                ).keyBy(
// Creates a BKeyedStream with key as hotel_id and value as PageViewCounter
                        x -> x.hotel_id
                ).reduceByKey( // reduce by hotel_id key
// a and b are values to reduce - 2 PageviewCounter objects - reduce function is summing counts
// reduceByKey returns a NON keyed stream of values (PageviewCounter)
                        (a,b) -> new PageviewCounter(a.hotel_id, a.hotel_area_code, a.count + b.count)
                ).map(
// calculation is done, map the PageviewCounter to OutputMessage
                        x -> new OutputMessage(x.hotel_area_code, x.hotel_id, x.count)
                );
    }

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();

        Scanner scanner = new Scanner(System.in);
        List<String> inputLines = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s == null || s.length()==0) break;
            inputLines.add(s);
        }
        System.out.println(inputLines);
        List<InputMessage> inputList = inputLines.stream()
                .map(x -> gson.fromJson(x, InputMessage.class))
                .collect(Collectors.toList());

//        String outputFilename = System.getenv("test.txt");
        String outputFilename = "test_stream.txt";
        PrintWriter printWriter = new PrintWriter(outputFilename);

        BStream<InputMessage> input = new BStream<>(inputList);
        BStream<OutputMessage> output = process(input);
        output.collectAsList()
                .stream()
                .sorted(Comparator.comparing( x -> x.hotel_area_code))
                .map(gson::toJson)
                .forEach(printWriter::println);
        printWriter.close();
    }
}
