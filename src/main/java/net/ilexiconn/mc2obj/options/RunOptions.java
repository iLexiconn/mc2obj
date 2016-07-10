/*
 */
package net.ilexiconn.mc2obj.options;

import com.beust.jcommander.Parameter;
import java.util.Arrays;
import java.util.List;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author RlonRyan
 */
public class RunOptions {
	
	@Parameter(names = {"--translate", "-t"}, arity = 3)
	public List<Float> translateList = Arrays.asList(0F, 0F, 0F);
	
	@Parameter(names= {"--stretch", "-s"}, arity = 2)
	public List<Float> stretchList = Arrays.asList(1F, 1F);
	
	@Parameter(names= {"--offset", "-o"}, arity = 2)
	public List<Float> offsetList = Arrays.asList(1F, 1F);
	
	public Vector3f translate = new Vector3f();
	
	public Vector2f stretch = new Vector2f(1, 1);
	
	public Vector2f offset = new Vector2f();
	
	public void convert() {
		this.translate = new Vector3f(translateList.get(0), translateList.get(1), translateList.get(2));
		this.stretch = new Vector2f(stretchList.get(0), stretchList.get(1));
		this.offset = new Vector2f(offsetList.get(0), offsetList.get(1));
	}
	
}
