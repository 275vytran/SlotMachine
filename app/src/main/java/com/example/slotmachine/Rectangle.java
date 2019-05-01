package com.example.slotmachine;

// 4-sided cube
public class Rectangle extends Mesh {
	public Rectangle(float width, float height) {
        width  /= 2;
        height /= 2;
 
        float vertices[] = {
        		-width, -height, 0, // point 0
                 width, -height, 0, // point 1
                 width,  height, 0, // point 2
                -width,  height, 0, // point 3
   
        };

        short indices[] = { 
        		0,2,3,	
        		0,1,2,
        };
     
        // Mapping coordinates for the vertices - this array needs to be same size as vertices array
		float textureCoordinates[] = { 
				0.0f, 0.75f, // vertex [0] 
				1.0f, 0.75f, // vertex [1]
				1.0f, 0.25f, // vertex [2]
				0.0f, 0.25f, // vertex [3]
		};   
        
	    setIndices(indices);
        setVertices(vertices);
		setTextureCoordinates(textureCoordinates);
    }
}
