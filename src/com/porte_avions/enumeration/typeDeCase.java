package com.porte_avions.enumeration;

public enum typeDeCase {
	SABLE(1), EAU(2), EAU_PA(3), EAU_AV(4), EAU_AVs(5), EAU_PA_AV(6), EAU_PA_AVs(
			7), SABLE_AV(8), SABLE_AVs(9), EAU_PA_SEL(30), EAU_AV_SEL(40), EAU_AVs_SEL(
			50), EAU_PA_AV_SEL(60), EAU_PA_AVs_SEL(70), SABLE_AV_SEL(80), SABLE_AVs_SEL(
			90);

	private int code;

	typeDeCase(final int code) {
		this.code = code;
	}
}
