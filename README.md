# STEPXML Converter

###### *** *This code and repository is not maintained by [Stibo Systems](http://www.stibosystems.com)* ***

This is a project to facilitate integration between STEP and downstream systems, this project does the conversion of STEP XML file into JSON using REST calls.

This is an open source project and is available so that each Stibo Systems customer can contribute and improve the development as needed.

Here's how the layout will look when you convert STEP XML to JSON

### STEPXML
```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- Configuration:
<STEP-ProductInformation ResolveInlineRefs="true" FollowOverrideSubProducts="true">
<Products ExportSize="Minimum">
<Product>
<Name/><AttributeLink/><ClassificationReference/><Product/>
<ProductCrossReference/><AssetCrossReference/><EntityCrossReference/><Values/><OverrideSubProduct/></Product></Products>
<Entities ExportSize="Minimum">
<Entity>
<Name/><AttributeLink/><ClassificationCrossReference/><Entity/>
<ProductCrossReference/><AssetCrossReference/><EntityCrossReference/><ContextCrossReference/><Values/></Entity></Entities>
</STEP-ProductInformation>

Export from AC-YU500A
Classifications All
Products "SU3-SalesItem-1111"
Assets All
-->
<STEP-ProductInformation ExportTime="2018-04-26 14:19:10" ExportContext="Context1" ContextID="Context1" WorkspaceID="Main" UseContextLocale="false">

  <Products>
    <Product ID="SU3-SalesItem-1111" UserTypeID="SalesItem" ParentID="SU3-Level3-111">
      <Name>AC-YU500A</Name>
      <ClassificationReference ClassificationID="SU1-WebLevel2-24" Type="WebsiteLink"/>

      <Values>
        <Value AttributeID="Height" UnitID="unece.unit.CMT">4.6</Value>
        <Value AttributeID="Color" ID="BK">Black</Value>
        <Value AttributeID="UPC">905034977623</Value>
        <Value AttributeID="Depth" UnitID="unece.unit.CMT">17</Value>
        <Value AttributeID="SellPackWidth" UnitID="unece.unit.CMT">16</Value>
        <Value AttributeID="SellPackDepth" UnitID="unece.unit.CMT">6</Value>
        <Value AttributeID="LaunchDate">2018-04-01</Value>
        <Value AttributeID="FeatureBullets">&lt;bulletlist&gt;&lt;bullet&gt;A comfortable design means they can be worn over your day-to-day glasses&lt;/bullet&gt;
&lt;bullet&gt;The glasses sync with your TV using a radio frequency signal, for uninterrupted 3D viewing&lt;/bullet&gt;
&lt;bullet&gt;Each pair of 3D glasses is adjustable and lightweight so they’re comfortable to wear during long movies&lt;/bullet&gt;
&lt;bullet&gt;Compatible with P, X and T series TVs&lt;/bullet&gt;&lt;/bulletlist&gt;</Value>
        <Value AttributeID="EAN">4905034977623</Value>
        <Value AttributeID="SellPackHeight" UnitID="unece.unit.CMT">4</Value>
        <Value AttributeID="SellPackWeight" UnitID="unece.unit.KGM">0.086</Value>
        <Value AttributeID="Width" UnitID="unece.unit.CMT">14.7</Value>
        <Value AttributeID="LongAdvertisingCopy">Active 3D Glasses</Value>
        <Value AttributeID="Warranty" UnitID="unece.unit.MON">24</Value>
        <Value AttributeID="ConsumerShortDescription">Active 3D Glasses</Value>
        <Value AttributeID="ShortAdvertisingCopy">Active 3D Glasses</Value>
        <Value AttributeID="Weight" UnitID="unece.unit.KGM">0.036</Value>
        <Value AttributeID="ManufacturerGLN" Derived="true">0743480060002</Value>
        <Value AttributeID="ManufacturerName" Derived="true">Acme Electronics, Inc.</Value>
        <Value AttributeID="CountryOfOrigin" Derived="true">GB</Value>
      </Values>
    </Product>
  </Products>
</STEP-ProductInformation>
```


### JSON
```json
{
    "STEP": [
        {
            "STEP-ProductInformation": {
                "ExportContext": "Context1",
                "ContextID": "Context1",
                "ExportTime": "2018-04-26 14:19:10",
                "WorkspaceID": "Main",
                "UseContextLocale": "false",
                "Products": [
                    {
                        "ID": "SU3-SalesItem-1111",
                        "Name": "AC-YU500A",
                        "UserTypeID": "SalesItem",
                        "ParentID": "SU3-Level3-111",
                        "ManufacturerGLN": {
                            "Derived": "true",
                            "Value": "0743480060002"
                        },
                        "CountryOfOrigin": {
                            "Derived": "true",
                            "Value": "GB"
                        },
                        "UPC": {
                            "Value": "905034977623"
                        },
                        "SellPackWidth": {
                            "UnitID": "unece.unit.CMT",
                            "Value": "16"
                        },
                        "ConsumerShortDescription": {
                            "Value": "Active 3D Glasses"
                        },
                        "Depth": {
                            "UnitID": "unece.unit.CMT",
                            "Value": "17"
                        },
                        "SellPackDepth": {
                            "UnitID": "unece.unit.CMT",
                            "Value": "6"
                        },
                        "Height": {
                            "UnitID": "unece.unit.CMT",
                            "Value": "4.6"
                        },
                        "Width": {
                            "UnitID": "unece.unit.CMT",
                            "Value": "14.7"
                        },
                        "LongAdvertisingCopy": {
                            "Value": "Active 3D Glasses"
                        },
                        "Warranty": {
                            "UnitID": "unece.unit.MON",
                            "Value": "24"
                        },
                        "ManufacturerName": {
                            "Derived": "true",
                            "Value": "Acme Electronics, Inc."
                        },
                        "Color": {
                            "Value": "Black",
                            "ID": "BK"
                        },
                        "SellPackHeight": {
                            "UnitID": "unece.unit.CMT",
                            "Value": "4"
                        },
                        "SellPackWeight": {
                            "UnitID": "unece.unit.KGM",
                            "Value": "0.086"
                        },
                        "Weight": {
                            "UnitID": "unece.unit.KGM",
                            "Value": "0.036"
                        },
                        "ClassificationReference": [
                            {
                                "ClassificationID": "SU1-WebLevel2-24",
                                "Type": "WebsiteLink"
                            }
                        ],
                        "EAN": {
                            "Value": "4905034977623"
                        },
                        "FeatureBullets": {
                            "Value": "<bulletlist><bullet>A comfortable design means they can be worn over your day-to-day glasses</bullet><bullet>The glasses sync with your TV using a radio frequency signal, for uninterrupted 3D viewing</bullet><bullet>Each pair of 3D glasses is adjustable and lightweight so they’re comfortable to wear during long movies</bullet><bullet>Compatible with P, X and T series TVs</bullet></bulletlist>"
                        },
                        "ShortAdvertisingCopy": {
                            "Value": "Active 3D Glasses"
                        },
                        "LaunchDate": {
                            "Value": "2018-04-01"
                        }
                    }
                ]
            }
        }
    ]
}
```

The project uses *Swagger* for API documentation, so to access the documentation just start the project and access the address: http://<server-address>:9090/swagger-ui.html