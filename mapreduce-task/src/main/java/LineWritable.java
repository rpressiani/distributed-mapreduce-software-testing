import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LineWritable implements WritableComparable<LineWritable> {
    private Text file;
    private IntWritable line;
    private Integer priority;

    public LineWritable() {
        this.file = new Text();
        this.line = new IntWritable();
    }

    public LineWritable(String file, int line) {
        this.file = new Text(file);
        this.line = new IntWritable(line);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        this.line.write(out);
        this.file.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.line.readFields(in);
        this.file.readFields(in);
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
        result = prime * result + ((this.line == null) ? 0 : this.line.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LineWritable)) {
            return false;
        }
        LineWritable other = (LineWritable) obj;
        if (this.file == null) {
            if (other.file != null) {
                return false;
            }
        }
        else if (!this.file.equals(other.file)) {
            return false;
        }
        if (this.line == null) {
            if (other.line != null) {
                return false;
            }
        }
        else if (!this.line.equals(other.line)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.file + "\t" + this.line;
    }

    @Override
    public int compareTo(LineWritable o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (o == this) {
            return 0;
        }
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
}
