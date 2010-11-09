<?xml version="1.0" encoding="UTF-8"?>
<sld:StyledLayerDescriptor xmlns="http://www.opengis.net/sld" xmlns:sld="http://www.opengis.net/sld" xmlns:ogc="http://www.opengis.net/ogc" xmlns:gml="http://www.opengis.net/gml" version="1.0.0">
    <sld:UserLayer>
        <sld:LayerFeatureConstraints>
            <sld:FeatureTypeConstraint/>
        </sld:LayerFeatureConstraints>
        <sld:UserStyle>
            <sld:Name>Default Styler</sld:Name>
            <sld:Title/>
            <sld:IsDefault>1</sld:IsDefault>
            <sld:FeatureTypeStyle>
                <sld:Name>simple</sld:Name>
                <sld:FeatureTypeName>Feature</sld:FeatureTypeName>
                <sld:SemanticTypeIdentifier>generic:geometry</sld:SemanticTypeIdentifier>
                <sld:SemanticTypeIdentifier>simple</sld:SemanticTypeIdentifier>
                <sld:Rule>
                    <sld:MaxScaleDenominator>1.280186E7</sld:MaxScaleDenominator>
                    <sld:PolygonSymbolizer>
                        <sld:Fill>
                            <sld:CssParameter name="fill">#FFF4C7</sld:CssParameter>
                            <sld:CssParameter name="fill-opacity">0.5</sld:CssParameter>
                        </sld:Fill>
                        <sld:Stroke>
                            <sld:CssParameter name="stroke">#B9BB7B</sld:CssParameter>
                        </sld:Stroke>
                    </sld:PolygonSymbolizer>
                </sld:Rule>
                <sld:Rule>
                <sld:MaxScaleDenominator>1011147.0</sld:MaxScaleDenominator>
                 <sld:TextSymbolizer>
                 
                        <sld:Label>
                            <ogc:PropertyName>nome</ogc:PropertyName>
                        </sld:Label>
                        <sld:Font>
                            <sld:CssParameter name="font-family">Sans</sld:CssParameter>
                            <sld:CssParameter name="font-size">5.0</sld:CssParameter>
                            <sld:CssParameter name="font-style">normal</sld:CssParameter>
                            <sld:CssParameter name="font-weight">bold</sld:CssParameter>
                        </sld:Font>
                        <sld:LabelPlacement>
                            <sld:PointPlacement>
                                <sld:AnchorPoint>
                                    <sld:AnchorPointX>
                                        <ogc:Literal>0.5</ogc:Literal>
                                    </sld:AnchorPointX>
                                    <sld:AnchorPointY>
                                        <ogc:Literal>0.5</ogc:Literal>
                                    </sld:AnchorPointY>
                                </sld:AnchorPoint>
                              
                            </sld:PointPlacement>
                        </sld:LabelPlacement>
                        <Halo>
                        	<Radius>
                        		<ogc:Literal>2</ogc:Literal>
                        	</Radius>
                        
                        	<sld:Fill>
                            		<sld:CssParameter name="fill">#FFFFFF</sld:CssParameter>
                            		<sld:CssParameter name="fill-opacity">0.65</sld:CssParameter>
                        	</sld:Fill>
                        </Halo>
                        <sld:Fill>
                            	<sld:CssParameter name="fill">#00000F</sld:CssParameter>
                        </sld:Fill>
                        <sld:VendorOption name="spaceAround">1</sld:VendorOption>
                    </sld:TextSymbolizer>
                </sld:Rule>  
            </sld:FeatureTypeStyle>
        </sld:UserStyle>
    </sld:UserLayer>
</sld:StyledLayerDescriptor>
