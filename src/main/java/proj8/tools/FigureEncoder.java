/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.tools;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import proj8.pojos.Figure;

/**
 *
 * @author brunocosta
 */
public class FigureEncoder implements Encoder.Text<Figure> {

    @Override
    public String encode(Figure object) throws EncodeException {
        return object.getJson().toString();
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

}
