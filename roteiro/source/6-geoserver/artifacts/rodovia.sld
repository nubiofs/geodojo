<?xml version="1.0" encoding="UTF-8"?>
<StyledLayerDescriptor version="1.0.0" xsi:schemaLocation="http://www.opengis.net/sld StyledLayerDescriptor.xsd"
  xmlns="http://www.opengis.net/sld" xmlns:ogc="http://www.opengis.net/ogc" xmlns:xlink="http://www.w3.org/1999/xlink"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <NamedLayer>
    <Name>Simple Roads</Name>
    <UserStyle>

      <Title>Default Styler for simple road segments</Title>
      <Abstract>Light red line, 2px wide</Abstract>
      <FeatureTypeStyle>
        <Rule>
          <Title>Roads</Title>
          <LineSymbolizer>
            <Stroke>
              <CssParameter name="stroke">
                <ogc:Literal>#AA3333</ogc:Literal>
              </CssParameter>
              <CssParameter name="stroke-width">
                <ogc:Literal>2</ogc:Literal>
              </CssParameter>
            </Stroke>
          </LineSymbolizer>
          <TextSymbolizer>
						<Label>
							<ogc:PropertyName>sigla</ogc:PropertyName>
					 	</Label>

						<Font>
							<CssParameter name="font-family">Times New Roman</CssParameter>
							<CssParameter name="font-style">Normal</CssParameter>
							<CssParameter name="font-size">10</CssParameter>
						</Font>
					
						<LabelPlacement>
						  <LinePlacement>
						    <PerpendicularOffset>10</PerpendicularOffset>	       
						 </LinePlacement>
						</LabelPlacement>

						<Halo>
							<Radius>
								<ogc:Literal>2</ogc:Literal>
							</Radius>
							<Fill>
								<CssParameter name="fill">#FFFFFF</CssParameter>
								<CssParameter name="fill-opacity">0.85</CssParameter>				
							</Fill>
						</Halo>

						<Fill>
							<CssParameter name="fill">#000000</CssParameter>
						</Fill>
						<VendorOption name="group">yes</VendorOption>
					</TextSymbolizer>
        </Rule>
      </FeatureTypeStyle>
    </UserStyle>
  </NamedLayer>
</StyledLayerDescriptor>
