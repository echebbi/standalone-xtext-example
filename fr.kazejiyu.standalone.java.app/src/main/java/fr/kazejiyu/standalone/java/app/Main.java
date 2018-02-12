package fr.kazejiyu.standalone.java.app;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.xtext.example.mydsl.MyDslStandaloneSetupGenerated;
import org.xtext.example.mydsl.myDsl.Model;

import com.google.inject.Injector;

public class Main {
	public static void main(String[] args) {
		Injector injector = new MyDslStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		Resource resource = resourceSet.getResource(URI.createFileURI("./hello-world.mydsl"), true);
		
		Model model = (Model) resource.getContents().get(0);
		model.getGreetings().forEach(greeting -> System.out.println(greeting.getName()));
	}
}
