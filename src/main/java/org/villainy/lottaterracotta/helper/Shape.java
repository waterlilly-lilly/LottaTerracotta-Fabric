package org.villainy.lottaterracotta.helper;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class Shape {
    public static Box getPixeledBox(double x0, double y0, double z0, double x1, double y1, double z1)
    { return new Box(x0/16.0, y0/16.0, z0/16.0, x1/16.0, y1/16.0, z1/16.0); }

    public static Box getRotatedBox(Box box, Direction facing, boolean horizontalRotation)
    {
        if(!horizontalRotation) {
            switch(facing.ordinal()) {
                case 0: return new Box(1-box.maxX, 1-box.maxZ, 1-box.maxY, 1-box.minX, 1-box.minZ, 1-box.minY); // D
                case 1: return new Box(1-box.maxX,   box.minZ,   box.minY, 1-box.minX,   box.maxZ,   box.maxY); // U
                case 2: return new Box(1-box.maxX,   box.minY, 1-box.maxZ, 1-box.minX,   box.maxY, 1-box.minZ); // N
                case 3: return new Box(  box.minX,   box.minY,   box.minZ,   box.maxX,   box.maxY,   box.maxZ); // S --> box
                case 4: return new Box(1-box.maxZ,   box.minY,   box.minX, 1-box.minZ,   box.maxY,   box.maxX); // W
                case 5: return new Box(  box.minZ,   box.minY, 1-box.maxX,   box.maxZ,   box.maxY, 1-box.minX); // E
            }
        } else {
            switch(facing.ordinal()) {
                case 0: return new Box(  box.minX, box.minY,   box.minZ,   box.maxX, box.maxY,   box.maxZ); // D --> box
                case 1: return new Box(  box.minX, box.minY,   box.minZ,   box.maxX, box.maxY,   box.maxZ); // U --> box
                case 2: return new Box(  box.minX, box.minY,   box.minZ,   box.maxX, box.maxY,   box.maxZ); // N --> box
                case 3: return new Box(1-box.maxX, box.minY, 1-box.maxZ, 1-box.minX, box.maxY, 1-box.minZ); // S
                case 4: return new Box(  box.minZ, box.minY, 1-box.maxX,   box.maxZ, box.maxY, 1-box.minX); // W
                case 5: return new Box(1-box.maxZ, box.minY,   box.minX, 1-box.minZ, box.maxY,   box.maxX); // E
            }
        }
        return box;
    }
}
