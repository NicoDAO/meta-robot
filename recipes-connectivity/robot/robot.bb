SUMMARY = "The canonical example of init scripts"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=22cd8fcef3454a96dde3f9f8d7274aae"
SRCREV = "7ca41f41cb82c6f6723e26912a1eb8c4cb9eb8ad"
SRC_BRANCH ??= "test_recipe_bitbake"
SRC_URI = "git://github.com/NicoDAO/lerobot.git;protocol=https;branch=${SRC_BRANCH}"
#PV = "git${SRCPV}"

S = "${WORKDIR}/git"
     
do_compile () {
	${CXX} ${CFLAGS} ${LDFLAGS} ${WORKDIR}/git/projRb/sources/*.cpp -Llibs -lpthread    -lrt -o ${WORKDIR}/robot
	${CXX} ${CFLAGS} ${LDFLAGS} ${WORKDIR}/git/projRb/telecomande/*.cpp -Llibs -lpthread  -lrt -o ${WORKDIR}/telecommande
}

do_install () {
 mkdir -p ${D}${bindir}/robot/bin
 cp  ${WORKDIR}/robot ${D}${bindir}/robot/bin #on copie les fichiers issus de la compilation dans /usr/bin/robot/bin/
 cp  ${WORKDIR}/telecommande ${D}${bindir}/robot/bin #on copie les fichiers issus de la compilation dans /usr/bin/robot/bin/
  
}

RDEPENDS_${PN} = "initscripts"

CONFFILES_${PN} += "${sysconfdir}/init.d/skeleton"
