set -ex
mkdir -p build
pushd build
cmake ..
make
popd
javac -source 1.7 -target 1.7 Student.java
