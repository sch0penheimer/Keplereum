export default function FromLatLongToXYZ(lat:number,lon:number,radius:number=4):Array<number>{
  
    const phi   = (90-lat)*(Math.PI/180);
    const theta = (lon+180)*(Math.PI/18)
    const x = -(radius * Math.sin(phi)*Math.cos(theta));
    const z = (radius * Math.sin(phi)*Math.sin(theta));
    const y = (radius * Math.cos(phi));
  
    return [x,y,z];

}