package org.kitfox.mvel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mvel2.MVEL;
import org.mvel2.Macro;
import org.mvel2.MacroProcessor;
import org.mvel2.ParserContext;
import org.mvel2.ast.ASTNode;
import org.mvel2.integration.Interceptor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.DefaultLocalVariableResolverFactory;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class TestMvel extends TestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestMvel.class);

    @Test
    public void testSimple() {
        ParserContext context = new ParserContext();
        context.addPackageImport("java.util");
        context.addPackageImport("org.kitfox.mvel");

        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("user2", new User());

        String expression =
                // Construire la memoire
                "user = new User();" +
                "user.id = 'gilles';" +
                "rolex = new Role(); rolex.role = 'admin';" +
                "user.roles = [rolex];" +
                "rolex = new Role(); rolex.role = 'factu';" +
                "user.roles.add(rolex);" +
                // "for(role : user.roles) { if(role.role == 'admin') return true; }" +
                // "allRoles = (role in user.roles);" +

                // Ceci est la simple ligne a effectuer pour tester le role admin!
                "return ($ in user.roles if $.role == 'admin') != empty;";
        // "return admin != empty;";
        Serializable s = MVEL.compileExpression(expression, context); // compile the expresion

        Object o = MVEL.executeExpression(s, vars);

        LOGGER.debug("testSimple {}", o);
    }

    @Test
    public void testFunction() {
        ParserContext context = new ParserContext();
        context.addPackageImport("java.util");
        context.addPackageImport("org.kitfox.mvel");

        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("user2", new User());
        vars.put("LOGGER", LOGGER);

        String expression =
                "def hello(a) { LOGGER.debug(\"Wow {}\", a); }" +
                        "hello('Hello!');" +
                        "def sum(a, b) { return a + b; }" +
                        "hello(sum( 5, 5));";
        Serializable s = MVEL.compileExpression(expression, context); // compile the expresion

        Object o = MVEL.executeExpression(s, vars);
        LOGGER.debug("testFunction {}", o);
    }

    @Test
    public void testLabmbda() {
        VariableResolverFactory factory = new DefaultLocalVariableResolverFactory();

        String expression =
                "threshold = def (x) { x >= 10 ? x : 0 }; " +
                        "result = 5 + threshold(12);";

        LOGGER.debug("testLabmbda {}", MVEL.eval(expression, factory));
    }

    @Test
    public void testMacro() {
        Map<String, Macro> myMacros = new HashMap<String, Macro>();

        Macro modifyMacro = new Macro() {
            @Override
            public String doMacro() {
                return "@Modify with";
            }
        };

        // Add modifyMacro to the Map
        myMacros.put("modify", modifyMacro);

        // Create the macro processor
        MacroProcessor macroProcessor = new MacroProcessor();

        // Add the macro map to the macro processor
        macroProcessor.setMacros(myMacros);

        // Now we pre-parse our expression
        String expression = "modify (obj) { value = 'foo' };";
        String parsedExpression = macroProcessor.parse(expression);

        LOGGER.debug("testMacro {}", parsedExpression);
    }

    @Test
    public void testInterceptors() {
        ParserContext context = new ParserContext();

        Map<String, Object> vars = new HashMap<String, Object>();
        Map<String, Macro> myMacros = new HashMap<String, Macro>();
        Map<String, Interceptor> myInterceptors = new HashMap<String, Interceptor>();

        Macro modifyMacro = new Macro() {
            @Override
            public String doMacro() {
                return "@Modify with";
            }
        };

        // Add modifyMacro to the Map
        myMacros.put("modify", modifyMacro);

        // Create the macro processor
        MacroProcessor macroProcessor = new MacroProcessor();

        // Add the macro map to the macro processor
        macroProcessor.setMacros(myMacros);

        // Create a simple interceptor.
        Interceptor myInterceptor = new Interceptor() {
            @Override
            public int doAfter(Object arg0, ASTNode arg1, VariableResolverFactory arg2) {
                LOGGER.debug("After! {} {} {}", arg0, arg1, arg2);
                return 0;
            }

            @Override
            public int doBefore(ASTNode arg0, VariableResolverFactory arg1) {
                LOGGER.debug("Before! {} {}", arg0, arg1);
                return 0;
            }
        };

        // Now add the interceptor to the map.
        myInterceptors.put("Intercept", myInterceptor);

        // Add the interceptors map to the parser context.
        context.setInterceptors(myInterceptors);
        context.setStrongTyping(true);

        // Now we pre-parse our expression
        String expression = "int total = 0; " +
                "@Intercept " +
                "foreach(int item : [1,2,3,4,5,6]) {" +
                "   total += item;" +
                "}" +
                "total;";

        Serializable s = MVEL.compileExpression(expression, context); // compile the expresion

        Object o = MVEL.executeExpression(s, vars);

        LOGGER.debug("testInterceptors {}", o);
    }

    @Test
    public void testSwing() {
        ParserContext context = new ParserContext();

        context.addImport(javax.swing.JFrame.class);
        context.addImport(javax.swing.JLabel.class);

        String expression = "" +
                "with (frame = new JFrame()) {" +
                "    title = 'My Swing Frame'," +
                "    resizable = true" +
                "}" +

            "frame.contentPane.add(new JLabel('My Label'));" +
            "frame.pack();" +
            "frame.visible = true;";

        VariableResolverFactory factory = new DefaultLocalVariableResolverFactory();
        Serializable s = MVEL.compileExpression(expression, context); // compile the expresion

        Object o = MVEL.executeExpression(s, factory);

        LOGGER.debug("testSwing {}", o);
    }

    @Test
    public void testTemplate() {
        String template = "1 + 1 = @{1+1}";

        // compile the template
        CompiledTemplate compiled = TemplateCompiler.compileTemplate(template);

        // execute the template
        String output = (String) TemplateRuntime.execute(compiled);

        LOGGER.debug("testTemplate {}", output);
    }
}
