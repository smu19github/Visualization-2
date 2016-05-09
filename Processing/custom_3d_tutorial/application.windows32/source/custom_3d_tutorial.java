import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class custom_3d_tutorial extends PApplet {

// 3D Geom Tutorial in Java mode
// Construct a lit and textured smooth Toroid and ground plane
// textures: http://img09.deviantart.net/5cd4/i/2009/173/9/f/seamless_wall_texture_06_by_caym.jpg, http://img04.deviantart.net/efba/i/2012/086/3/a/seamless_tileable_grass_texture_by_demolitiondan-d4u41a9.jpg

// Ira Greenberg, 2015

/*** Data Structures ***/
// Vertex: PVector(x, y, z), PVector(nx, ny, nz), RGBA(r, g, b, a), UV(u, v)
// Index: elem0, elem1, elem2
// Face: v0, v1, v2
// abstract Geom3

/*** Instructions ***/
// Up arrow for smooth render
// Down arrow for faceted render (default)

Plane p;
Toroid t;

public void setup() {
  
  p = new Plane(this, new RGBA(50, 50, 50, 255), "grass.jpg", 4200, 4200);
  t = new Toroid(this, new RGBA(120, 120, 120, 255), "stone.jpg", 180, 75, 18, 18);
  noStroke();
}
public void draw() {
  background(0);
  ambientLight(30, 30, 30);
  emissive(23, 0, 0);
  lightSpecular(255, 255, 255);
  pointLight(255, 255, 255, -100, -100, 800);
  pointLight(150, 150, 150, -100, 100, 800);
  specular(0, 255, 255);
  shininess(30);

  pushMatrix();
  translate(width/2, height/2, -1000);
  rotateX(PI/3);
  rotateZ(frameCount*PI/1360);
  scale(1.2f, 1.2f);
  p.display();
  popMatrix();

  pushMatrix();
  translate(width/2, height/2.75f, 0);
  rotateY(frameCount*PI/520);
  rotateX(-frameCount*PI/720);
  t.display();
  popMatrix();
}

public void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP) {
      t.setSmooth(true);
    } else if (keyCode == DOWN) {
      t.setSmooth(false);
    }
  }
}
  public void settings() {  size(1024, 800, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "custom_3d_tutorial" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
