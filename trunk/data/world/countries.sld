<?xml version="1.0" encoding="ISO-8859-1"?>
<StyledLayerDescriptor version="1.0.0" 
    xsi:schemaLocation="http://www.opengis.net/sld StyledLayerDescriptor.xsd" 
    xmlns="http://www.opengis.net/sld" 
    xmlns:ogc="http://www.opengis.net/ogc" 
    xmlns:xlink="http://www.w3.org/1999/xlink" 
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <NamedLayer>
    <Name>Countries</Name>
    <UserStyle>
      <FeatureTypeStyle>
        <Rule>
          <PolygonSymbolizer>
            <Stroke>

              <CssParameter name="stroke">#FFFF00</CssParameter>
            </Stroke>
          </PolygonSymbolizer>
          <TextSymbolizer>
           <Label>
             <ogc:PropertyName>NAME</ogc:PropertyName>

           </Label>
           <Font>
             <CssParameter name="font-family">Arial</CssParameter>
           </Font>
           <Halo>
             <Radius>
               <ogc:Literal>2</ogc:Literal>
             </Radius>

             <Fill>
               <CssParameter name="fill">#000000</CssParameter>
             </Fill>
           </Halo>
           <Fill>
               <CssParameter name="fill">#FFFFFF</CssParameter>
           </Fill>
          </TextSymbolizer>

        </Rule>
      </FeatureTypeStyle>
    </UserStyle>
  </NamedLayer>
</StyledLayerDescriptor>