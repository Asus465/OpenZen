package shit.zen.hud;

import lombok.Getter;
import lombok.Setter;
import lombok.Generated;
import shit.zen.event.impl.GlRenderEvent;
import shit.zen.event.impl.Render2DEvent;
import shit.zen.modules.Category;
import shit.zen.modules.Module;
import shit.zen.utils.render.RenderUtil;

public abstract class HudElement
extends Module {
    @Getter @Setter
    protected float x;
    @Getter @Setter
    protected float y;
    protected float hudWidth;
    protected float hudHeight;
    @Getter
    private boolean dragging = false;
    @Getter @Setter
    private float dragOffsetX;
    @Getter @Setter
    private float dragOffsetY;
    protected boolean visible;

    public HudElement(String string) {
        super(string, Category.RENDER);
    }

    public abstract void onRender2D(Render2DEvent var1, float var2, float var3);

    public abstract void onGlRender(GlRenderEvent var1, float var2, float var3);

    public abstract void onSettings();

    public boolean mousePressed(int mouseX, int mouseY, int button) {
        if (this.isHovered(mouseX, mouseY) && button == 0) {
            this.dragging = true;
            this.dragOffsetX = (float)mouseX - this.getX();
            this.dragOffsetY = (float)mouseY - this.getY();
            return true;
        }
        return false;
    }

    public void mouseDragged(int mouseX, int mouseY) {
        this.x = (float)mouseX - this.dragOffsetX;
        this.y = (float)mouseY - this.dragOffsetY;
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return RenderUtil.isHovered(this.x, this.y, this.hudWidth, this.hudHeight, mouseX, mouseY);
    }

    public void stopDragging() {
        this.dragging = false;
    }

    @Generated
    public float getWidth() {
        return this.hudWidth;
    }

    @Generated
    public float getHeight() {
        return this.hudHeight;
    }

    @Override
    @Generated
    public boolean isEnabled() {
        return this.visible;
    }

    @Generated
    public void setWidth(float width) {
        this.hudWidth = width;
    }

    @Generated
    public void setHeight(float height) {
        this.hudHeight = height;
    }

    @Generated
    protected void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    @Override
    @Generated
    public void setEnabled(boolean enabled) {
        this.visible = enabled;
    }
}