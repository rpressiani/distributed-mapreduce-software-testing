import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Map2 extends Mapper<LongWritable, Text, LineWritable2, Text> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] parts = line.split("\t");
        String[] tests = parts[2].split(",");

        String className = parts[0];
        Integer lineNumber = Integer.parseInt(parts[1]);
        Integer numTests = Integer.parseInt(tests[tests.length - 1]);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tests.length - 1; i++) {
            result.append(tests[i]);
            if (i < tests.length - 2) result.append(",");
        }

        context.write(new LineWritable2(className, lineNumber, numTests), new Text(result.toString()));

    }



}
