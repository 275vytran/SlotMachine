package com.example.slotmachine;

// 4-sided cube
public class Spinner extends Mesh {
	public Spinner(float width, float height, float depth) {
        width  /= 2;
        height /= 2;
        depth  /= 2;
        float y;
        y = height*0.57735026919f;
 
        float vertices[] = {
        		-width, -y  , -depth, // [0]  point 0  //back
                 width, -y  , -depth, // [1]  point 1
                 width,  y  , -depth, // [2]  point 2
                -width,  y  , -depth, // [3]  point 3
                             
                 width,  y  , -depth, // [4]  point 2 //back-top
                -width,  y  , -depth, // [5]  point 3
				 width,  height,      0, // [6]  point 8
				-width,  height,      0, // [7]  point 9

				 width,  height,      0, // [8]  point 8 //front-top
				-width,  height,      0, // [9]  point 9
				 width,  y  ,  depth, // [10]  point 6
				-width,  y  ,  depth, // [11]  point 7

				-width, -y  ,  depth, // [12]  point 4 //front
                 width, -y  ,  depth, // [13]  point 5
				 width,  y  ,  depth, // [14] point 6
				-width,  y  ,  depth, // [15] point 7

				-width, -y  , -depth, // [16]  point 0 //back-bottom
				 width, -y  , -depth, // [17]  point 1
				-width, -height,      0, // [18] point 10
				 width, -height,      0, // [19] point 11

				-width, -height,      0, // [20] point 10 //front-bottom
				 width, -height,      0, // [21] point 11
				-width, -y  ,  depth, // [22]  point 4
				 width, -y  ,  depth, // [23]  point 5
        };

        short indices[] = { 
        		0,2,1, 		// back
        		0,3,2,
        		7,4,5,		// back-top
        		7,6,4,
        		11,8,9,	    // front-top
        		11,10,8,
        		12,14,15,	    //front
        		12,13,14,
				18,16,17,      //back-bottom
				18,17,19,
				22,21,23,      //front-bottom
				22,20,21
        };
     
        // Mapping coordinates for the vertices - this array needs to be same size as vertices array
		float textureCoordinates[] = { 
				0.0f , 2/3f,	// vertex [0] back
				1/3f, 2/3f,	// vertex [1]
				1/3f, 1.0f ,	// vertex [2]
				0.0f,  1.0f ,	// vertex [3]
				
				1/3f, 1/3f,	// vertex [4] back-top
				0.0f, 1/3f, // vertex [5]
				1/3f, 2/3f, // vertex [6]
				0.0f, 2/3f, // vertex [7]
				
				1.0f, 0.0f, // vertex [8] front-top
				2/3f, 0f, // vertex [9]
				1.0f, 1/3f, // vertex [10]
				2/3f, 1/3f, // vertex [11]

				1/3f, 2/3f, // vertex [12] front
				2/3f, 2/3f, // vertex [13]
				2/3f, 1/3f, // vertex [14]
				1/3f, 1/3f, // vertex [15]

				2/3f, 2/3f, // vertex [16] back-bottom
				1.0f, 2/3f, // vertex [17]
				2/3f, 1/3f, // vertex [18]
				1.0f, 1/3f, // vertex [19]

				2/3f, 1.0f, // vertex [20] front-botton
				1.0f, 1.0f, // vertex [21]
				2/3f, 2/3f, // vertex [22]
				1.0f, 2/3f, // vertex [23]
		};   
        
	    setIndices(indices);
        setVertices(vertices);
		setTextureCoordinates(textureCoordinates);
    }
}
