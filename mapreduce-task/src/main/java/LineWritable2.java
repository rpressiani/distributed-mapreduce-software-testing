import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LineWritable2 implements WritableComparable<LineWritable2> {
    private Text file;
    private IntWritable line;
    private IntWritable numTests;


    public LineWritable2() {
        this.file = new Text();
        this.line = new IntWritable();
        this.numTests = new IntWritable();
    }

    public LineWritable2(String file, int line, int numTests) {
        this.file = new Text(file);
        this.line = new IntWritable(line);
        this.numTests = new IntWritable(numTests);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        this.line.write(out);
        this.file.write(out);
        this.numTests.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.line.readFields(in);
        this.file.readFields(in);
        this.numTests.readFields(in);
    }

    public Text getFile() {
        return this.file;
    }

    public void setFile(Text file) {
        this.file = file;
    }

    public IntWritable getLine() {
        return this.line;
    }

    public void SetLine(IntWritable line) {
        this.line = line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineWritable2 that = (LineWritable2) o;

        if (file != null ? !file.equals(that.file) : that.file != null) return false;
        if (line != null ? !line.equals(that.line) : that.line != null) return false;
        return numTests != null ? numTests.equals(that.numTests) : that.numTests == null;
    }

    @Override
    public int hashCode() {
        int result = file != null ? file.hashCode() : 0;
        result = 31 * result + (line != null ? line.hashCode() : 0);
        result = 31 * result + (numTests != null ? numTests.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return file + " " + line;
    }

    @Override
    public int compareTo(LineWritable2 o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (o == this) {
            return 0;
        }
        if (this.numTests.compareTo(o.numTests) < 0) {
            return 1;
        }
        else if (this.numTests.compareTo(o.numTests) == 0) {
            if (this.file.compareTo(o.file) > 0) {
                return 1;
            }
            else if (this.file.compareTo(o.file) == 0) {
                return this.line.compareTo(o.line);
            }
            else {
                return -1;
            }
        }
        else {
            return -1;
        }
    }
}
