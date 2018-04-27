package com.step.converter.infrastructure.repositories.path;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class XmlFiles implements FilenameFilter {

	private String extension;

	private String fileExtension = ".xml";

	@Value( "${service.stepxml.path}" )
	private String stepXmlPath;

	@Value( "${log.error.stepxmlReader}" )
	private String stepxmlReaderError;

	public XmlFiles() {

		extension = fileExtension;
	}

	public List<Document> getXmlFiles() throws Exception {

		List<Document> documents = new ArrayList<>();

		File xmlFile;
		Document doc;
		DocumentBuilder dBuilder;
		DocumentBuilderFactory dbFactory;

		String path;

		try {

			File file = new File( stepXmlPath );

			FilenameFilter filter = new XmlFiles();

			String[] files = file.list( filter );

			if ( files != null && files.length > 0 ) {

				for ( String xml : files ) {

					if ( xml != null && !xml.trim().isEmpty() ) {

						path = stepXmlPath;
						path = path.concat( "/" ).concat( xml );

						xmlFile = new File( path );

						dbFactory = DocumentBuilderFactory.newInstance();
						dBuilder = dbFactory.newDocumentBuilder();
						doc = dBuilder.parse( xmlFile );

						doc.getDocumentElement().normalize();

						documents.add( doc );
					}
				}
			}
		} catch ( ParserConfigurationException parserException ) {

			log.error( stepxmlReaderError + parserException.getMessage() );

			throw new Exception( parserException );
		}

		return documents;
	}

	@Override
	public boolean accept( File dir, String name ) {

		return name.toLowerCase().endsWith( fileExtension.toLowerCase() );
	}
}