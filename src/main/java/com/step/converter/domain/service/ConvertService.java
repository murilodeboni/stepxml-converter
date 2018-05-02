package com.step.converter.domain.service;

import com.step.converter.infrastructure.exception.StepConverterException;
import com.step.converter.infrastructure.repositories.path.XmlFilesRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import java.util.List;

import static org.apache.commons.lang.StringEscapeUtils.*;

@Slf4j
@Service
public class ConvertService {

	@Autowired
	private XmlFilesRepository xmlFilesRepository;

	public JSONObject convertStepXmlFile() throws StepConverterException {

		List<Document> documents = xmlFilesRepository.getXmlFiles();

		return this.convertXmlToJson( documents );
	}

	public JSONObject convertXmlToJson( List<Document> documents ) throws StepConverterException {

		JSONObject jsonObj;
		StringBuffer json;

		StringBuffer allJson = new StringBuffer();

		try {

			if ( documents == null || documents.isEmpty() ) {

				allJson.append( "{ \"STEP\" : [] }" );
			}

			String attributeValue;
			int productCount = 0;
			int attributesCount = 0;
			int valueClassificationCount = 0;

			for ( Document doc : documents ) {

				if ( allJson.length() > 0 ) {

					allJson.append( ", " );

				} else {

					allJson.append( "{ \"STEP\" : [ " );
				}

				json = new StringBuffer();

				Element stepProductElement = doc.getDocumentElement();

				json.append( "{ " )
						.append( "\"" )
						.append( stepProductElement.getNodeName() )
						.append( "\" : { " );

				NamedNodeMap stepProductAttributesNodeMap = stepProductElement.getAttributes();

				if ( stepProductAttributesNodeMap != null && stepProductAttributesNodeMap.getLength() > 0 ) {

					for ( int i = 0; i < stepProductAttributesNodeMap.getLength(); i++ ) {

						if ( i > 0 ) {

							json.append( ", " );
						}

						Node stepProductAttributesNode = stepProductAttributesNodeMap.item( i );

						json.append( "\"" )
								.append( stepProductAttributesNode.getNodeName() )
								.append( "\" : \"" )
								.append( stepProductAttributesNode.getNodeValue() )
								.append( "\"" );
					}

					NodeList productsNodeList = stepProductElement.getElementsByTagName( "Products" );

					if ( productsNodeList != null && productsNodeList.getLength() > 0 ) {

						for ( int i = 0; i < productsNodeList.getLength(); i++ ) {

							Node productsNode = productsNodeList.item( i );

							json.append( ", \"" )
									.append( productsNode.getNodeName() )
									.append( "\" : [ " );

							NodeList productNodeList = productsNode.getChildNodes();

							if ( productNodeList != null && productNodeList.getLength() > 0 ) {

								for ( int j = 0; j < productNodeList.getLength(); j++ ) {

									Node productNode = productNodeList.item( j );

									if ( productNode.getNodeName().trim().equalsIgnoreCase( "#text" ) ) {

										continue;
									}

									if ( productCount > 0 ) {

										json.append( ", " );
									}

									NamedNodeMap attributesNodeMap = productNode.getAttributes();

									json.append( "{ " );

									if ( attributesNodeMap != null && attributesNodeMap.getLength() > 0 ) {

										for ( int attCount = 0; attCount < attributesNodeMap.getLength(); attCount++ ) {

											if( attCount > 0 ) {

												json.append( ", " );
											}

											Node attributeNode = attributesNodeMap.item( attCount );

											json.append( "\"" )
													.append( attributeNode.getNodeName() )
													.append( "\" : \"" )
													.append( attributeNode.getNodeValue() )
													.append( "\"" );
										}
									}

									NodeList valuesNodeList = productNode.getChildNodes();

									if ( valuesNodeList != null && valuesNodeList.getLength() > 0 ) {

										for ( int valuesCount = 0; valuesCount < valuesNodeList.getLength(); valuesCount++ ) {

											Node valuesNode = valuesNodeList.item( valuesCount );

											if ( valuesNode.getNodeName().trim().equalsIgnoreCase( "#text" ) ) {

												continue;
											}

											if ( valuesNode.getNodeName().trim().equalsIgnoreCase( "Name" ) ) {

												json.append( ", \"" )
													.append( valuesNode.getNodeName() )
													.append( "\" : \"" )
													.append( valuesNode.getFirstChild().getNodeValue() )
													.append( "\" " );
											}

											if ( valuesNode.getNodeName().trim().equalsIgnoreCase( "ClassificationReference" ) ) {

												valueClassificationCount++;

												if ( valueClassificationCount == 1 ) {

													json.append( ", \"" )
															.append( valuesNode.getNodeName() )
															.append( "\" : [ {" );

												} else {

													json.append( ", { " );
												}

												NamedNodeMap classificationReferenceNodeMap = valuesNode.getAttributes();

												if ( classificationReferenceNodeMap != null && classificationReferenceNodeMap.getLength() > 0 ) {

													for ( int classificationCount = 0; classificationCount < classificationReferenceNodeMap.getLength(); classificationCount++ ) {

														if ( classificationCount > 0 ) {

															json.append( ", " );
														}

														Node classificationReferenceNode = classificationReferenceNodeMap.item( classificationCount );

														json.append( "\"" )
																.append( classificationReferenceNode.getNodeName() )
																.append( "\" : \"" )
																.append( classificationReferenceNode.getNodeValue() )
																.append( "\"" );
													}
												}

												json.append( "} ] " );
											}
										}

										valueClassificationCount = 0;
									}

									NodeList valueNodeList = stepProductElement.getElementsByTagName( "Value" );

									if ( valueNodeList != null && valueNodeList.getLength() > 0 ) {

										for( int valueCount = 0; valueCount < valueNodeList.getLength(); valueCount++ ) {

											Node valueNode = valueNodeList.item( valueCount );

											NamedNodeMap valueNamedNode = valueNode.getAttributes();

											if ( valueNamedNode != null && valueNamedNode.getLength() > 0 ) {

												json.append( ", \"" )
														.append( valueNamedNode.getNamedItem( "AttributeID" ).getNodeValue() )
														.append( "\" : { " );

												for ( int valueNamedCount = 0; valueNamedCount < valueNamedNode.getLength(); valueNamedCount++ ) {

													Node valueAttribute = valueNamedNode.item( valueNamedCount );

													if ( valueAttribute.getNodeName().trim().equalsIgnoreCase( "#text" ) ) {

														continue;
													}

													if ( !valueAttribute.getNodeName().trim().equalsIgnoreCase( "AttributeID" ) ) {

														attributesCount++;

														if ( attributesCount > 1 ) {

															json.append( ", " );
														}

														json.append( "\"" )
																.append( valueAttribute.getNodeName() )
																.append( "\" : \"" )
																.append( valueAttribute.getNodeValue() )
																.append( "\"" );
													}
												}

												if ( attributesCount > 0 ) {

													json.append( ", " );
												}

												attributeValue = valueNode.getFirstChild().getNodeValue();

												json.append( "\"Value\" : " );

												if ( attributeValue != null && ! attributeValue.trim().isEmpty() ) {

													attributeValue = attributeValue.replaceAll( "\\n", "" );
													attributeValue = unescapeHtml( attributeValue );
													attributeValue = unescapeXml( attributeValue );

													json.append( "\"" )
															.append( attributeValue )
															.append( "\"" );
												}

												json.append( " } " );

												attributesCount = 0;
											}
										}
									}

									json.append( " } " );

									productCount++;
								}

								productCount = 0;
							}

							json.append( " ]" );
						}
					}
				}

				json.append( " } }" );

				allJson.append( json );
			}

			allJson.append( " ] }" );

			log.info( "Generated JSON: " + allJson.toString() );

			jsonObj = new JSONObject( allJson.toString() );

		} catch ( Exception e ) {

			throw new StepConverterException( e );
		}

		return jsonObj;
	}
}