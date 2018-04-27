package com.step.converter.domain.service;

import com.step.converter.infrastructure.repositories.path.XmlFiles;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import java.util.List;

@Slf4j
@Service
public class ConvertService {

	private String extension;

	@Autowired
	private XmlFiles xmlFiles;

	public JSONObject convertXmlToJson() throws Exception {

		StringBuffer allJson = new StringBuffer();
		JSONObject jsonObj = null;
		StringBuffer json;

		try {

			List<Document> documents = xmlFiles.getXmlFiles();

			if ( documents != null && !documents.isEmpty() ) {

				Node node;
				Element element;
				NamedNodeMap namedNodeMap;

				for ( Document doc : documents ) {

					if ( allJson.length() > 0 ) {

						allJson.append( ", " );

					} else {

						allJson.append( "{ \"STEP\" : [ " );
					}

					json = new StringBuffer();

					element = doc.getDocumentElement();

					json.append( "{ " )
							.append( "\"" )
							.append( element.getNodeName() )
							.append( "\" : { " );

					namedNodeMap = element.getAttributes();

					if ( namedNodeMap != null && namedNodeMap.getLength() > 0 ) {

						for ( int i = 0; i < namedNodeMap.getLength(); i++ ) {

							if ( i > 0 ) {

								json.append( ", " );
							}

							node = namedNodeMap.item( i );

							json.append( "\"" )
									.append( node.getNodeName() )
									.append( "\" : \"" )
									.append( node.getNodeValue() )
									.append( "\"" );
						}
					}

					json.append( " } }" );

					allJson.append( json );
				}
			}

			allJson.append( " ] }" );

			jsonObj = new JSONObject( allJson.toString() );

		} catch ( Exception e ) {

			throw new Exception( e );
		}

		return jsonObj;
	}
}