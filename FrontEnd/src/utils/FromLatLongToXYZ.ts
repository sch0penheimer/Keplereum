export default function FromLatLongToXYZ(lat:number,lon:number,radius:number=4):Array<number>{
  
    let phi   = (90-lat)*(Math.PI/180);
    let theta = (lon+180)*(Math.PI/180);

    let x = -(radius * Math.sin(phi)*Math.cos(theta));
    let z = (radius * Math.sin(phi)*Math.sin(theta));
    let y = (radius * Math.cos(phi));
  
    return [x,y,z];

}