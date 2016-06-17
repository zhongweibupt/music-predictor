/**
 * 
 */
package MusicPredictor;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;

/**
* <p>Title: TextArrayWritable.java</p>
* <p>Description: CSV文件行中各个字段的数据。</p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: Zhongwei</p>
* @author Zhongwei
* @date 2016年6月15日
* @version 1.0
*/
public class TextArrayWritable extends ArrayWritable {
	
	public TextArrayWritable() {
		super(Text.class);
	}

	public TextArrayWritable(Text[] strings) {
		super(Text.class, strings);
	}
}
