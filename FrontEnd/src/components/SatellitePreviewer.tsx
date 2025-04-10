import { useEffect, useRef } from "react";
import { useSatelliteContext } from "@/contexts/SatelliteContext";
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';
import { loadSatelliteModel } from "@/utils/satelliteUtils";

const SatellitePreviewer = () => {
  const { selectedSatellite } = useSatelliteContext();
  const containerRef = useRef<HTMLDivElement>(null);
  const sceneRef = useRef(new THREE.Scene());
  const cameraRef = useRef<THREE.PerspectiveCamera>();
  const rendererRef = useRef<THREE.WebGLRenderer>();
  const controlsRef = useRef<OrbitControls>();
  const groupRef = useRef<THREE.Group>(new THREE.Group());

  useEffect(() => {
    if (!containerRef.current || !selectedSatellite?.glbPath) return;

    // Setup
    const container = containerRef.current;
    const width = container.clientWidth;
    const height = container.clientHeight;

    const scene = sceneRef.current;
    const camera = new THREE.PerspectiveCamera(75, width / height, 0.1, 1000);
    camera.position.set(0, 0, 0.5);
    camera.lookAt(0, 0, 0);
    cameraRef.current = camera;

    const renderer = new THREE.WebGLRenderer({ antialias: true });
    renderer.setSize(width, height);
    renderer.setPixelRatio(window.devicePixelRatio);
    container.appendChild(renderer.domElement);
    rendererRef.current = renderer;

    const controls = new OrbitControls(camera, renderer.domElement);
    controls.enableDamping = true;
    controlsRef.current = controls;

    // Add satellite
    const group = groupRef.current;
    loadSatelliteModel(selectedSatellite.glbPath, new THREE.Object3D(), group);
    scene.add(group);

    // Handle window resize
    const handleResize = () => {
      const newWidth = container.clientWidth;
      const newHeight = container.clientHeight;
      camera.aspect = newWidth / newHeight;
      camera.updateProjectionMatrix();
      renderer.setSize(newWidth, newHeight);
    };

    window.addEventListener("resize", handleResize);

    // Animation loop
    const animate = () => {
      requestAnimationFrame(animate);
      controls.update();
      renderer.render(scene, camera);
    };
    animate();

    return () => {
      window.removeEventListener("resize", handleResize);
      if (renderer.domElement.parentNode) {
        renderer.domElement.parentNode.removeChild(renderer.domElement);
      }
      controls.dispose();
      renderer.dispose();
      scene.clear();
    };
  }, [selectedSatellite?.glbPath]);

  return (
    <div className="w-full h-4/5 bg-black">
      <div ref={containerRef} className="mx-auto w-5/6 h-5/6" />
    </div>
  );
};

export default SatellitePreviewer;
