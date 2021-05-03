package org.gwtproject.validation.apt;

import java.util.Set;

import javax.lang.model.element.TypeElement;

import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.ext.Generator;
import org.gwtproject.validation.ext.StandardGeneratorContext;
import org.gwtproject.validation.rebind.ValidatorGenerator;
import org.gwtproject.validation.rebind.ext.TreeLogger;
import org.gwtproject.validation.rebind.ext.UnableToCompleteException;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 11/11/18
 */
public class ClientBundleClassBuilder {

    private final TreeLogger logger;
    private final AptContext context;

    private final Set<TypeElement> elements;

    public ClientBundleClassBuilder(TreeLogger logger, AptContext context, Set<TypeElement> elements) {
        this.logger = logger;
        this.context = context;
        this.elements = elements;
    }

    public void process() throws UnableToCompleteException {
        Generator generator = new ValidatorGenerator();

        StandardGeneratorContext standardGeneratorContext = new StandardGeneratorContext(context);
        for (TypeElement type : elements) {
            generator.generate(logger, standardGeneratorContext, type);
        }
    }
}
