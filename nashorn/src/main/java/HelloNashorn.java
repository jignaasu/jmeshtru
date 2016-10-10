import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

/**
 * Created by abhijith.nagarajan on 10/10/16.
 */
public class HelloNashorn {

	private ScriptEngine scriptEngine;

	public HelloNashorn() {
		scriptEngine = new NashornScriptEngineFactory().getScriptEngine();
	}


	public static void main(String[] args) {
		String javascript = "var str = 'Hello Nashorn'; print(str);";
		new HelloNashorn().evalJavaScript(javascript);
	}

	public void evalJavaScript(String javascript) {
		try {
			scriptEngine.eval(javascript);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
