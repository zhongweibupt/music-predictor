/**
 * 
 */
package MusicPredictor;

import java.io.IOException;  

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Reducer;

/**
* <p>Title: AffairReduce.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: Zhongwei</p>
* @author Zhongwei
* @date 2016年6月16日
* @version 1.0
*/
public class AffairReducer extends Reducer<Text, Text, Text, Iterable<Text>> {
	public void reduce(Text key, Iterable<Text> values, OutputCollector<Text, Iterable<Text>> output, Reporter reporter) 
			throws IOException,InterruptedException{
		output.collect(key, values);
	}
}
