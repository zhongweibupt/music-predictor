/**
 * 
 */
package MusicPredictor;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
* <p>Title: SongsMapper.java</p>
* <p>Description: 重写Mapper接口，实现map()方法，从mars_tianchi_songs文件得到键值对。</p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: Zhongwei</p>
* @author Zhongwei
* @date 2016年6月15日
* @version 1.0
*/

/*
public interface SongsMapper extends Mapper<LongWritable, TextArrayWritable, Text, Text> {
	public void map(LongWritable key, TextArrayWritable value, OutputCollector<Text, Text> output, Reporter reporter) 
			throws IOException;
}*/


public class SongsMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	private String delimiter = null;
	
	@Override
	public void setup(Context context)
	{
		delimiter= context.getConfiguration().get("delimiter", ","); 
	}
	
	/**
	 * @MethodName     : public void map(LongWritable key, TextArrayWritable value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException
	 * @Description    : 读取mars_tianchi_songs.csv每一行各个字段数据，song_id和artist_id分别作为键值。
	 * @param args     : LongWritable key, TextArrayWritable value, OutputCollector<Text, Text> output, Reporter reporter
	 */
	
	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapred.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapred.OutputCollector, org.apache.hadoop.mapred.Reporter)
	 */
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String[] values = value.toString().split(delimiter);
		Text resultKey = new Text(values[0]);
		Text resultValue = new Text(values[1]);
		output.collect(resultKey,resultValue);
	}
}
